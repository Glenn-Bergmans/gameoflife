package be.cegeka.gameoflife.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

public class Generation {

    private Map<Position, Cell> grid;

    public Generation(List<List<Boolean>> world) {
        grid = findAllLivingCellsIn(world);
        assignLivingNeighboursToCells();
    }

    private Map<Position, Cell> findAllLivingCellsIn(List<List<Boolean>> world) {
        Map<Position, Cell> grid = new HashMap<>();
        for(int row = 0; row < world.size(); row++) {
            for(int column = 0; column < world.get(0).size(); column++) {
                boolean cellIsAlive = world.get(row).get(column);
                if(cellIsAlive) {
                    Position position = new Position(row, column);
                    grid.put(position, new Cell(true, position));
                }
            }
        }
        return grid;
    }

    private List<Cell> getNeighboursOf(Cell cell) {
        Position position = cell.getPosition();
        return Stream.of(
            getCellAt(Position.topLeftOf(position)),
            getCellAt(Position.above(position)),
            getCellAt(Position.topRightOf(position)),
            getCellAt(Position.leftOf(position)),
            getCellAt(Position.rightOf(position)),
            getCellAt(Position.bottomLeftOf(position)),
            getCellAt(Position.below(position)),
            getCellAt(Position.bottomRightOf(position))
        ).collect(toList());
    }

    private int amountOfLivingNeighboursOf(Cell cell) {
        Position position = cell.getPosition();
        return (int) Stream.of(
            grid.get(Position.topLeftOf(position)),
            grid.get(Position.above(position)),
            grid.get(Position.topRightOf(position)),
            grid.get(Position.leftOf(position)),
            grid.get(Position.rightOf(position)),
            grid.get(Position.bottomLeftOf(position)),
            grid.get(Position.below(position)),
            grid.get(Position.bottomRightOf(position))
        ).filter(Objects::nonNull)
            .count();
    }

    private void assignLivingNeighboursToCells() {
        grid.values().forEach(cell -> cell.setAmountOfLivingNeighbours(amountOfLivingNeighboursOf(cell)));
    }

    private Cell getCellAt(Position position) {
        return grid.getOrDefault(position, generateDeadCellAt(position));
    }

    private Cell generateDeadCellAt(Position position) {
        Cell cell = new Cell(false, position);
        cell.setAmountOfLivingNeighbours(amountOfLivingNeighboursOf(cell));
        return cell;
    }

    public Generation( Map<Position, Cell> livingCells) {
        this.grid = livingCells;
        assignLivingNeighboursToCells();
    }

    public List<List<Boolean>> asNestedList(int size) {
        List<List<Boolean>> nestedList = new ArrayList<>();
        for(int row = 0; row < size; row++) {
            nestedList.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                nestedList.get(row).add(getCellAt(new Position(row, column)).isAlive());
            }
        }
        return nestedList;
    }

    public Generation nextGeneration() {
        Map<Position, Cell> livingCellsInNextGeneration = grid.values().stream()
            .map(this::getNeighboursOf)
            .flatMap(Collection::stream)
            .distinct()
            .map(Cell::nextGeneration)
            .filter(Cell::isAlive)
            .collect(Collectors.toMap(Cell::getPosition, identity()));
        return new Generation(livingCellsInNextGeneration);
    }

}
