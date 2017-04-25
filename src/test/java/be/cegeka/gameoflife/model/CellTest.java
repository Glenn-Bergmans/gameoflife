package be.cegeka.gameoflife.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(true, new Position(1, 1));
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenFewerThanTwoLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell.setAmountOfLivingNeighbours(1);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenTwoLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        cell.setAmountOfLivingNeighbours(2);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenThreeLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        cell.setAmountOfLivingNeighbours(3);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenLiveCell_WhenMoreThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell.setAmountOfLivingNeighbours(4);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenExactlyThreeLiveNeighbours_ThenShouldReturnLiveCell() throws Exception {
        // GIVEN
        cell = new Cell(false, new Position(1,1));
        cell.setAmountOfLivingNeighbours(3);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isTrue();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenLessThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell = new Cell(false, new Position(1,1));
        cell.setAmountOfLivingNeighbours(2);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

    @Test
    public void nextGeneration_GivenDeadCell_WhenMoreThanThreeLiveNeighbours_ThenShouldReturnDeadCell() throws Exception {
        // GIVEN
        cell = new Cell(false, new Position(1,1));
        cell.setAmountOfLivingNeighbours(4);

        // WHEN
        Cell actual = cell.nextGeneration();

        // THEN
        assertThat(actual.isAlive()).isFalse();
    }

}
