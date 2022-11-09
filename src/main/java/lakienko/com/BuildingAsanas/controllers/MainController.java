package lakienko.com.BuildingAsanas.controllers;

import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private AsanaRepository asanaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(Principal principal, Model model){

        Iterable<Asana> asanas = asanaRepository.findAll();
        model.addAttribute("asana", asanas);

        return "index";
    }




    @GetMapping("/about-us")
    public String about(@RequestParam(name = "username",required = false, defaultValue = "Hello")
                            String username ,Model model){
        model.addAttribute("name",username);
        return "about-us";
    }

}
