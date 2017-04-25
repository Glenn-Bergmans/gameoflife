package be.cegeka.gameoflife.model;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static Position leftOf(Position position) {
        return new Position(position.getRow(), position.getColumn() - 1);
    }

    public static Position rightOf(Position position) {
        return new Position(position.getRow(), position.getColumn() + 1);
    }

    public static Position above(Position position) {
        return new Position(position.getRow() - 1, position.getColumn());
    }

    public static Position below(Position position) {
        return new Position(position.getRow() + 1, position.getColumn());
    }

    public static Position topLeftOf(Position position) {
        return new Position(position.getRow() - 1, position.getColumn() - 1);
    }

    public static Position topRightOf(Position position) {
        return new Position(position.getRow() - 1, position.getColumn() + 1);
    }

    public static Position bottomLeftOf(Position position) {
        return new Position(position.getRow() + 1, position.getColumn() - 1);
    }

    public static Position bottomRightOf(Position position) {
        return new Position(position.getRow() + 1, position.getColumn() + 1);
    }

    // auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    // auto-generated
    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
