package be.cegeka.gameoflife.model;

import java.util.ArrayList;
import java.util.List;

public class Generation {

    private Cell[][] cells;

    public Generation(List<List<Boolean>> world) {
        cells = convertToCells(world);
        assignNeighboursToCells();
    }

    private Cell[][] convertToCells(List<List<Boolean>> world) {
        int dimension = world.size();
        Cell[][] cells = new Cell[dimension][dimension];
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                boolean alive = world.get(i).get(j);
                cells[i][j] = new Cell(alive);
            }
        }
        return cells;
    }

    private void assignNeighboursToCells() {
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[0].length; j++) {
                List<Cell> neighbours = findNeighboursOfCellAt(i, j);
                cells[i][j].setNeighbours(neighbours);
            }
        }
    }

    private List<Cell> findNeighboursOfCellAt(int xCoordinate, int yCoordinate) {
        List<Cell> neighbours = new ArrayList<>();
        int rangeLeft = xCoordinate > 0 ? 1 : 0;
        int rangeRight = xCoordinate < cells.length - 1 ? 1 : 0;
        int rangeTop = yCoordinate > 0 ? 1 : 0;
        int rangeBottom = yCoordinate < cells[0].length - 1 ? 1 : 0;
        for(int i = xCoordinate - rangeLeft; i <= xCoordinate + rangeRight; i++) {
            for(int j = yCoordinate - rangeTop; j <= yCoordinate + rangeBottom; j++) {
                if(! (i == xCoordinate && j == yCoordinate)) {
                    neighbours.add(cells[i][j]);
                }
            }
        }
        return neighbours;
    }

    public Generation(Cell[][] cells) {
        this.cells = cells;
    }

    public List<List<Boolean>> asNestedList() {
        List<List<Boolean>> nestedList = new ArrayList<>();
        for(int i = 0; i < cells.length; i++) {
            nestedList.add(new ArrayList<>());
            for(int j = 0; j < cells[0].length; j++) {
                nestedList.get(i).add(cells[i][j].isAlive());
            }
        }
        return nestedList;
    }

    public Generation nextGeneration() {
        Cell[][] nextGeneration = new Cell[cells.length][cells[0].length];
        for(int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                nextGeneration[i][j] = cells[i][j].nextGeneration();
            }
        }
        return new Generation(nextGeneration);
    }
}
