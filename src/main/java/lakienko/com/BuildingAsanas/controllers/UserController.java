package lakienko.com.BuildingAsanas.controllers;

import lakienko.com.BuildingAsanas.models.Role;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //Доделать проблему с отображением ошибки...
    @GetMapping("/login")
    public String login(@RequestParam(name = "error", defaultValue = "", required = true) String error, Model model) {

        if (error.equals("error")){
            model.addAttribute("error","Поля не должны быть пустыми!");
        }

        return "login";
    }


    @PostMapping("/login")
    public String loginCheck(@RequestParam String username,
                             @RequestParam String password
    ){

        if(username.length() < 1 || password.length() < 1)
            return "redirect:/login?error=error";

        return "redirect:/login";
    }


    @GetMapping("/user")
    public String user(@RequestParam(name = "error", defaultValue = "", required = false)
                           String error, Principal principal, Model model){

         User user = userRepository.findByUsername(principal.getName());
         model.addAttribute("email", user.getEmail());

        if (error.equals("password"))
            model.addAttribute("error", "Поля не должны быть пустыми!");

        return "user";
    }


    @PostMapping("/user/update")
    public String updateUser(Principal principal, User userForm){

        User user = userRepository.findByUsername(principal.getName());
        user.setEmail(userForm.getEmail());
        String pass = passwordEncoder.encode(userForm.getPassword());
        user.setRoles(userForm.getRoles());
        user.setPassword(pass);

        if (userForm.getPassword().length() < 1 || userForm.getEmail().length() < 1)
            return "redirect:/user?error=password";
        else
            userRepository.save(user);

        return "redirect:/user";
    }



    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {

       if (error.equals("username"))
           model.addAttribute("error", "Такой логин пользователя уже занят");

       if (error.equals("password"))
           model.addAttribute("error", "Поля не должны быть пустыми!");

        return "reg";
    }


    @PostMapping("/reg")
    public String addUser(
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {

        if (userRepository.findByUsername(username) != null)
            return "redirect:/reg?error=username";

        password = passwordEncoder.encode(password);
        User user = new User(username,password,email, true, Collections.singleton(Role.USER));

        if (username.length() < 1 || email.length() < 1 || password.length() < 1)
            return "redirect:/reg?error=password";
        else
            userRepository.save(user);


        return "redirect:/login";
    }


}
