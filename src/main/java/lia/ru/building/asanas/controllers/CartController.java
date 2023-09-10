package lia.ru.building.asanas.controllers;


import lia.ru.building.asanas.models.Asana;
import lia.ru.building.asanas.models.User;
import lia.ru.building.asanas.models.UserAsanas;
import lia.ru.building.asanas.repository.AsanaRepository;
import lia.ru.building.asanas.repository.UserAsanasRepository;
import lia.ru.building.asanas.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final UserRepository userRepository;

    private final AsanaRepository asanaRepository;

    private final UserAsanasRepository userAsanasRepository;

    @GetMapping("/cart-asanas")
    public String userLoaded(@RequestParam(name = "error", defaultValue = "", required = false) String error,
                             Principal principal, Model model) {

        var user = userRepository.findByUsername(principal.getName());
        var all = userAsanasRepository.findAll();
        var listUserAsanas = all.stream()
                .filter(userAsanas -> Objects.equals(userAsanas.getUser().getId(), user.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        model.addAttribute("user_comment", user.getCommentOfUser());
        model.addAttribute("user_asanas", listUserAsanas);

        return "cart-asanas";
    }


    @PostMapping("/cart-asanas/{id}/add")
    public String addAsanaCartPost(@PathVariable(value = "id") long id, Principal principal) {

        User userCurrent = userRepository.findByUsername(principal.getName());
        Asana asanaCurrent = asanaRepository.findById(id).orElse(new Asana());

        var build = UserAsanas.builder()
                .asana(asanaCurrent)
                .user(userCurrent)
                .build();

        userAsanasRepository.save(build);

        return "redirect:/cart-asanas/";
    }


    @PostMapping("/cart-asanas/{id}/delete")
    public String deleteCurrentAsana(@PathVariable(value = "id") long id) {

        userAsanasRepository.deleteById(id);

        return "redirect:/cart-asanas/";
    }


    @GetMapping("/cart-asanas/user-comment")
    public String userLoadedAsanas(@RequestParam(name = "error", defaultValue = "", required = false) String error,
                                   Principal principal, Model model) {

        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("user_comment", user.getCommentOfUser());

        if (error.equals("comment"))
            model.addAttribute("error", "Комментарий не должен быть пустым.");

        return "user-comment";
    }


    @PostMapping("/cart-asanas/user-comment")
    public String updateUserComment(
            Principal principal,
            @RequestParam String comment) {

        User user = userRepository.findByUsername(principal.getName());
        user.setCommentOfUser(comment);

        if (comment.length() < 1)
            return "redirect:/cart-asanas/user-comment?error=comment";
        else
            userRepository.save(user);

        return "redirect:/cart-asanas";
    }
}
