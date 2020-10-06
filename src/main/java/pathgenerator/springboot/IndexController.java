package pathgenerator.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * This is the IndexController and this class handles the / serves the Index.html webpage to user
 * @author Nicholas Blackburn
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String main(Model model) {
        return "index";
    }
    
}
