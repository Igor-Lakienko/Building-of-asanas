package lakienko.com.BuildingAsanas.controllers;


import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;
import lakienko.com.BuildingAsanas.repositories.UserAsanasRepository;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@Controller
public class CartController {

    UserAsanas userAsanas = new UserAsanas();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AsanaRepository asanaRepository;

    @Autowired
    private UserAsanasRepository userAsanasRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/cart-asanas")
    public String userLoaded(@RequestParam(name ="error", defaultValue = "",required = false) String error,
                                 Principal principal,Model model){

        User user = userRepository.findByUsername(principal.getName());
        Set<UserAsanas> user_asanas = user.getUserAsanas();
        String user_comment = user.getComment();

        model.addAttribute("user_comment", user_comment);
        model.addAttribute("user_asanas", user_asanas);

        return "cart-asanas";
    }


    @PostMapping("/cart-asanas/{id}/add")
    public String addAsanaCartPost(@PathVariable(value = "id") long id,Principal principal){

        User userCurrent = userRepository.findByUsername(principal.getName());
        Asana asanaCurrent = asanaRepository.findById(id).orElse(new Asana());

        userAsanas = new UserAsanas();
        userAsanas.setUser(userCurrent);
        userAsanas.setAsana(asanaCurrent);

        userAsanasRepository.save(userAsanas);

        return "redirect:/cart-asanas/";
    }


    @PostMapping("/cart-asanas/{id}/delete")
    public String deleteCurrentAsana(@PathVariable(value = "id") long id) {

        UserAsanas userAsanas = userAsanasRepository.findById(id).orElse(new UserAsanas());
        userAsanasRepository.deleteById(id);

        return "redirect:/cart-asanas/";
    }


    @GetMapping("/cart-asanas/user-comment")
    public String userLoadedAsanas(@RequestParam(name ="error", defaultValue = "",required = false) String error,
                             Principal principal,Model model){

        User user = userRepository.findByUsername(principal.getName());
        String user_comment = user.getComment();

        model.addAttribute("user_comment", user_comment);

        if(error.equals("comment"))
            model.addAttribute("error","Комментарий не должен быть пустым.");

        return "user-comment";
    }


    @PostMapping("/cart-asanas/user-comment")
    public String updateUserComment(Principal principal,User userForm){

        User user = userRepository.findByUsername(principal.getName());
        user.setComment(userForm.getComment());

        if (userForm.getComment().length() < 1)
            return "redirect:/cart-asanas/user-comment?error=comment";
        else
            userRepository.save(user);

        return "redirect:/cart-asanas";
    }


}
