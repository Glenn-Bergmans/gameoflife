package be.cegeka.gameoflife.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(true);
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenFewerThanTwoLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        List<Cell> neighbours = asList(new Cell(true));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenTwoLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(false));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenThreeLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(true));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenMoreThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenExactlyThreeLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        cell.setAlive(false);
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(true), new Cell(false));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenLessThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell.setAlive(false);
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(false), new Cell(false));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenMoreThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell.setAlive(false);
        List<Cell> neighbours = asList(new Cell(true), new Cell(true), new Cell(true), new Cell(true));
        cell.setNeighbours(neighbours);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

}
