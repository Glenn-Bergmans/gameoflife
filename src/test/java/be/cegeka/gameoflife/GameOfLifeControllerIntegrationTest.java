package be.cegeka.gameoflife;

import be.cegeka.GameoflifeApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = GameoflifeApplication.class)
public class GameOfLifeControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetApplicationName_ShouldReturnApplicationName() throws Exception {
        // GIVEN
        List<List<Boolean>> world = blinker();

        // WHEN
        ResultActions actual = mockMvc.perform(
                post("/gameoflife/world")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(world.toString())
            );

        // THEN
        actual.andExpect(status().isOk())
            .andExpect(content().string("[[false,true,false],[false,true,false],[false,true,false]]"));
    }

    private List<List<Boolean>> blinker() {
        List<Boolean> emptyRow = asList(false, false, false);
        List<Boolean> fullRow = asList(true, true, true);
        return asList(emptyRow, fullRow, emptyRow);
    }
}
