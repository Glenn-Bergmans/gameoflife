package be.cegeka.gameoflife.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    private Position position;

    @Before
    public void setUp() throws Exception {
        position = new Position(1,1);
    }

    @Test
    public void leftOf() throws Exception {
        // WHEN
        Position actual = Position.leftOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(1);
        assertThat(actual.getColumn()).isEqualTo(0);
    }

    @Test
    public void rightOf() throws Exception {
        // WHEN
        Position actual = Position.rightOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(1);
        assertThat(actual.getColumn()).isEqualTo(2);
    }

    @Test
    public void above() throws Exception {
        // WHEN
        Position actual = Position.above(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(0);
        assertThat(actual.getColumn()).isEqualTo(1);
    }

    @Test
    public void below() throws Exception {
        // WHEN
        Position actual = Position.below(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(2);
        assertThat(actual.getColumn()).isEqualTo(1);
    }

    @Test
    public void topLeftOf() throws Exception {
        // WHEN
        Position actual = Position.topLeftOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(0);
        assertThat(actual.getColumn()).isEqualTo(0);
    }

    @Test
    public void topRightOf() throws Exception {
        // WHEN
        Position actual = Position.topRightOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(0);
        assertThat(actual.getColumn()).isEqualTo(2);
    }

    @Test
    public void bottomLeftOf() throws Exception {
        // WHEN
        Position actual = Position.bottomLeftOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(2);
        assertThat(actual.getColumn()).isEqualTo(0);
    }

    @Test
    public void bottomRightOf() throws Exception {
        // WHEN
        Position actual = Position.bottomRightOf(position);

        // THEN
        assertThat(actual.getRow()).isEqualTo(2);
        assertThat(actual.getColumn()).isEqualTo(2);
    }

}
