package by.marshallbaby.simplewebapp.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Web Page Controller", description = "Simply returns web page :)")
public class WebPageController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
