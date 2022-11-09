package lakienko.com.BuildingAsanas.controllers;


import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;
import lakienko.com.BuildingAsanas.repositories.UserAsanasRepository;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


@Controller
public class CartController {



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AsanaRepository asanaRepository;
    @Autowired
    private UserAsanasRepository userAsanasRepository;


    @GetMapping("/cart-asanas")
    public String userLoaded(Principal principal,Model model){

        User user = userRepository.findByUsername(principal.getName());
        Set<UserAsanas> user_asanas = user.getUserAsanas();

        model.addAttribute("user_asanas", user_asanas);

        return "cart-asanas";
    }

    @PostMapping("/cart-asanas/{id}/add")
    public String addAsanaCartPost(@PathVariable(value = "id") long id,
                                   Principal principal){

        User userCurrent = userRepository.findByUsername(principal.getName());
        Asana asanaCurrent = asanaRepository.findById(id).orElse(new Asana());

        UserAsanas userAsanas = new UserAsanas();
        userAsanas.setUser(userCurrent);
        userAsanas.setAsana(asanaCurrent);

        userAsanasRepository.save(userAsanas);

        return "redirect:/cart-asanas/";
    }

//    public Set<UserAsanas> sort(Set<UserAsanas> list){
//
//        UserAsanas userAsanas = new UserAsanas();
//        long array = userAsanas.getId();
//
//        for (int i = 0; i < array; i++) {
//
//        }
//
//    }

}
