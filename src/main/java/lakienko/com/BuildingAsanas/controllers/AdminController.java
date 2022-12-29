package lakienko.com.BuildingAsanas.controllers;

import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);

        return "admin";
    }


    @GetMapping("/admin/user-{id}")
    public String userReviews (@PathVariable(value = "id") long id,Model model){

        User user = userRepository.findById(id).orElse(new User());
        Set<UserAsanas> user_asanas = user.getUserAsanas();
        String user_comment = user.getComment();

        model.addAttribute("title", "Асаны пользователя: " + user.getUsername());
        model.addAttribute("user_asanas", user_asanas);
        model.addAttribute("user_comment", user_comment);

        return "user-asanas";
    }
}
