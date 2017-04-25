package be.cegeka.gameoflife.model;

public class Cell {

    private boolean alive;
    private Position position;
    private int amountOflivingNeighbours;

    public Cell(boolean alive, Position position) {
        this.alive = alive;
        this.position = position;
    }

    public void setAmountOfLivingNeighbours(int amountOflivingNeighbours) {
        this.amountOflivingNeighbours = amountOflivingNeighbours;
    }

    public Cell nextGeneration() {
        long amountOfLivingNeighbours = amountOfLivingNeighbours();
        if(amountOfLivingNeighbours < 2 || amountOfLivingNeighbours > 3 || (amountOfLivingNeighbours == 2 && ! alive)) {
            return new Cell(false, position);
        }
        return new Cell(true, position);
    }

    private int amountOfLivingNeighbours() {
        return amountOflivingNeighbours;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (alive != cell.alive) return false;
        if (amountOflivingNeighbours != cell.amountOflivingNeighbours) return false;
        return position != null ? position.equals(cell.position) : cell.position == null;
    }

    @Override
    public int hashCode() {
        int result = (alive ? 1 : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + amountOflivingNeighbours;
        return result;
    }
}
