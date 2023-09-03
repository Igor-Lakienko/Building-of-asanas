package lia.ru.building.asanas.controllers;


import lia.ru.building.asanas.models.Asana;
import lia.ru.building.asanas.models.User;
import lia.ru.building.asanas.models.UserAsanas;
import lia.ru.building.asanas.repository.AsanaRepository;
import lia.ru.building.asanas.repository.UserAsanasRepository;
import lia.ru.building.asanas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

@Controller
public class CartController {

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
        Iterable<UserAsanas> allById = userAsanasRepository.findAllById(Collections.singleton(user.getId()));

//        Set<UserAsanas> user_asanas = user.getUserAsanas();
//        String user_comment = user.getCommentOfUser();

        model.addAttribute("user_comment", user.getCommentOfUser());
        model.addAttribute("user_asanas", allById);

        return "cart-asanas";
    }


    @PostMapping("/cart-asanas/{id}/add")
    public String addAsanaCartPost(@PathVariable(value = "id") long id,Principal principal){

        User userCurrent = userRepository.findByUsername(principal.getName());
        Asana asanaCurrent = asanaRepository.findById(id).orElse(new Asana());

        var build = UserAsanas.builder()
                .asana(asanaCurrent)
                .user(userCurrent)
                .build();

        //todo не сохраняется asana_id.
//        userAsanasRepository.save(build);

        return "redirect:/cart-asanas/";
    }


    @PostMapping("/cart-asanas/{id}/delete")
    public String deleteCurrentAsana(@PathVariable(value = "id") long id) {

//        UserAsanas userAsanas = userAsanasRepository.findById(id).orElse(new UserAsanas());
        userAsanasRepository.deleteById(id);

        return "redirect:/cart-asanas/";
    }


    @GetMapping("/cart-asanas/user-comment")
    public String userLoadedAsanas(@RequestParam(name ="error", defaultValue = "",required = false) String error,
                             Principal principal,Model model){

        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("user_comment", user.getCommentOfUser());

        if(error.equals("comment"))
            model.addAttribute("error","Комментарий не должен быть пустым.");

        return "user-comment";
    }


    @PostMapping("/cart-asanas/user-comment")
    public String updateUserComment(
            Principal principal,
            @RequestParam String comment){

        User user = userRepository.findByUsername(principal.getName());
        user.setCommentOfUser(comment);

        if (comment.length() < 1)
            return "redirect:/cart-asanas/user-comment?error=comment";
        else
            userRepository.save(user);

        return "redirect:/cart-asanas";
    }
}
