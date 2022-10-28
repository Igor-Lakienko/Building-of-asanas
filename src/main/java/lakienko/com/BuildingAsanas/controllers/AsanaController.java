package lakienko.com.BuildingAsanas.controllers;

import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;
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
    public String add(){
        return "add-asana";
    }


    @PostMapping("/asana/add")
    public String adding(
                        @AuthenticationPrincipal User user,
                        @RequestParam String title,
                        @RequestParam String image,
                        @RequestParam String info,
                        @RequestParam String fullInfo){

        Asana asana = new Asana(title, info, image, fullInfo, user);
        asanaRepository.save(asana);

        return "redirect:/";
    }


    @GetMapping("/asana/{id}")
    public String showAsana(@PathVariable(value = "id") long id, Model model){
        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        model.addAttribute("asana", asana);
        return "show-asana";
    }


    @GetMapping("/asana/{id}/update")
    public String update(@PathVariable(value = "id") long id, Model model){
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
                         @RequestParam String fullInfo){

        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        asana.setTitle(title);
        asana.setImage(image);
        asana.setInfo(info);
        asana.setFullInfo(fullInfo);
        asanaRepository.save(asana);

        return "redirect:/asana/" + id;
    }


    @PostMapping("/asana/{id}/delete")
    public String deleteAsana( @PathVariable(value = "id") long id){
        Asana asana = asanaRepository.findById(id).orElse(new Asana());
        asanaRepository.delete(asana);
        return "redirect:/";
    }



}
