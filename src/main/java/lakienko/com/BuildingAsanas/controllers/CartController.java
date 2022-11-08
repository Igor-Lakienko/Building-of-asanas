package lakienko.com.BuildingAsanas.controllers;


import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;

import lakienko.com.BuildingAsanas.repositories.UserAsanasRepository;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import lakienko.com.BuildingAsanas.service.UserAsanasServices;
import lakienko.com.BuildingAsanas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.Set;


@Controller

public class CartController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AsanaRepository asanaRepository;
    @Autowired
    private UserAsanasRepository userAsanasRepository;
    @Autowired
    private UserAsanasServices userAsanasServices;




   @GetMapping("/cart-asanas/{id}")
    public String userReviews (@PathVariable(value = "id") long id, Model model) {

       User user = userRepository.findById(id).orElse(new User());
       System.out.println(user.getId());
       System.out.println(user.getUsername());

       Set<UserAsanas> asanas = user.getUserAsanas();
       System.out.println(asanas);



       model.addAttribute("asanas", asanas);

        return "cart-asanas";

    }
}
