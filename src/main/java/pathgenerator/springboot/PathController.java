package pathgenerator.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this class is for controlling the Data passed Via Path html to java backend
 * @author Nicholas Blackburn
 */
@Controller
@RequestMapping("/path")
public class PathController {
    
    @GetMapping("pathCreator")
    public String pathcreator(){
        return "pathCreator";
    }

    @GetMapping("pathDisplay")
    public String pathDisplay(){
        return "pathDisplay";
    }
}
