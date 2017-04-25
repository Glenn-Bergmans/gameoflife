package be.cegeka.gameoflife.model;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class GenerationTest {

    private Generation generation;

    @Test
    public void asNestedList_GivenWorld_ThenReturnSameContent() throws Exception {
        // GIVEN
        List<List<Boolean>> expected = blinker();
        generation = new Generation(expected);

        // WHEN
        List<List<Boolean>> actual = generation.asNestedList(3);

        // THEN
        assertThat(actual.size()).isEqualTo(3);
        assertThat(actual.get(0)).containsExactlyElementsOf(expected.get(0));
        assertThat(actual.get(1)).containsExactlyElementsOf(expected.get(1));
        assertThat(actual.get(2)).containsExactlyElementsOf(expected.get(2));
    }

    @Test
    public void nextGeneration_GivenBlinker_ThenReturnRotated() throws Exception {
        // GIVEN
        List<List<Boolean>> blinker = blinker();
        generation = new Generation(blinker);

        // WHEN
        List<List<Boolean>> actual = generation.nextGeneration().asNestedList(3);

        // THEN
        assertThat(actual.size()).isEqualTo(3);
        List<Boolean> expectedRow = asList(false, true, false);
        assertThat(actual.get(0)).containsExactlyElementsOf(expectedRow);
        assertThat(actual.get(1)).containsExactlyElementsOf(expectedRow);
        assertThat(actual.get(2)).containsExactlyElementsOf(expectedRow);
    }

    private List<List<Boolean>> blinker() {
        List<Boolean> emptyRow = asList(false, false, false);
        List<Boolean> fullRow = asList(true, true, true);
        return asList(emptyRow, fullRow, emptyRow);
    }

}
