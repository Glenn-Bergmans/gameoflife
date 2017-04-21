package be.cegeka.gameoflife;

import be.cegeka.gameoflife.model.Generation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/gameoflife")
public class GameOfLifeController {

    private static Logger logger = Logger.getLogger(GameOfLifeController.class);

    @RequestMapping(value = "/world", method = POST)
    @ResponseBody
    public List<List<Boolean>> getWorld(@RequestBody List<List<Boolean>> currentWorld){
        //logger.info(currentWorld);
        return new Generation(currentWorld).nextGeneration().asNestedList();
    }

}
