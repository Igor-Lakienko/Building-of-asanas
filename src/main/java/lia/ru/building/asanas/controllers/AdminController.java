package lia.ru.building.asanas.controllers;

import lia.ru.building.asanas.models.User;
import lia.ru.building.asanas.models.UserAsanas;
import lia.ru.building.asanas.repository.UserAsanasRepository;
import lia.ru.building.asanas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAsanasRepository userAsanasRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "admin";
    }


    @GetMapping("/admin/user-{id}")
    public String userReviews(@PathVariable(value = "id") long id, Model model) {

        User user = userRepository.findById(id).orElse(new User());
        Iterable<UserAsanas> listUserAsanas = userAsanasRepository.findAllById(Collections.singleton(id));

        String user_comment = user.getCommentOfUser();

        model.addAttribute("title", "Асаны пользователя: " + user.getUsername());
        model.addAttribute("user_asanas", listUserAsanas);
        model.addAttribute("user_comment", user_comment);

        return "user-asanas";
    }
}
