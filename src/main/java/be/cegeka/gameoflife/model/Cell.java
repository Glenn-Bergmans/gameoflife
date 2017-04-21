package be.cegeka.gameoflife.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private boolean alive;
    private List<Cell> neighbours = new ArrayList<>();

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public void setNeighbours(List<Cell> cells) {
        neighbours = cells;
    }

    public Cell nextGeneration() {
        long amountOfLivingNeighbours = amountOfLivingNeighbours();
        if(amountOfLivingNeighbours < 2 || amountOfLivingNeighbours > 3 || (amountOfLivingNeighbours == 2 && ! alive)) {
            return new Cell(false);
        }
        return new Cell(true);
    }

    private long amountOfLivingNeighbours() {
        return neighbours.stream().filter(Cell::isAlive).count();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
