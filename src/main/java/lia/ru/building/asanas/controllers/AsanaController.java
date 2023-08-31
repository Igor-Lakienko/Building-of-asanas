package lia.ru.building.asanas.controllers;

import lia.ru.building.asanas.models.Asana;
import lia.ru.building.asanas.models.User;
import lia.ru.building.asanas.repositories.AsanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AsanaController {

    @Autowired
    private AsanaRepository asanaRepository;


    @GetMapping("/asana/add")
    public String add(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {

        if (error.equals("empty"))
            model.addAttribute("error", "Поля не должны быть пустыми!");

        return "add-asana";
    }


    @PostMapping("/asana/add")
    public String adding(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String image,
            @RequestParam String info,
            @RequestParam String fullInfo,
            @RequestParam String positiveEffects,
            @RequestParam String negativeEffects) {

        var asana = Asana.builder()
                .title(title)
                .image(image)
                .user(user)
                .positiveEffects(positiveEffects)
                .negativeEffects(negativeEffects)
                .build();

//        new Asana(title, info, image, fullInfo, user, positiveEffects, negativeEffects);

        if (asana.getTitle().length() < 1 || asana.getImage().length() < 1 || asana.getInfo().length() < 1
                || asana.getPositiveEffects().length() < 1 || asana.getNegativeEffects().length() < 1)
            return "redirect:/asana/add?error=empty";

        else
            asanaRepository.save(asana);

        return "redirect:/";
    }


    @GetMapping("/asana/{id}")
    public String showAsana(@PathVariable(value = "id") long id, Model model) {
        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        model.addAttribute("asana", asana);

        return "show-asana";
    }


    @GetMapping("/asana/{id}/update")
    public String update(@PathVariable(value = "id") long id,
                         @RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {

        if (error.equals("empty"))
            model.addAttribute("error", "Поля не должны быть пустыми!");

        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        model.addAttribute("asana", asana);

        return "asana-update";
    }


    @PostMapping("/asana/{id}/update")
    public String updateAsana(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam String image,
            @RequestParam String info,
            @RequestParam String fullInfo,
            @RequestParam String positiveEffects,
            @RequestParam String negativeEffects) {

        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        asana.setTitle(title);
        asana.setImage(image);
        asana.setInfo(info);
        asana.setPositiveEffects(positiveEffects);
        asana.setNegativeEffects(negativeEffects);

        if (title.length() < 1 || image.length() < 1 || info.length() < 1 || fullInfo.length() < 1
                || positiveEffects.length() < 1 || negativeEffects.length() < 1)
            return "redirect:/asana/" + id + "/update?error=empty";
        else
            asanaRepository.save(asana);

        return "redirect:/asana/" + id;
    }


    @PostMapping("/asana/{id}/delete")
    public String deleteAsana(@PathVariable(value = "id") long id) {
        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        asanaRepository.delete(asana);

        return "redirect:/";
    }

}
