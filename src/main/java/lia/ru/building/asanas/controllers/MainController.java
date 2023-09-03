package lia.ru.building.asanas.controllers;

import lia.ru.building.asanas.models.Asana;
import lia.ru.building.asanas.repository.AsanaRepository;
import lia.ru.building.asanas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private AsanaRepository asanaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(@RequestParam(name = "value",defaultValue = "All",required = false)String value,
                        Model model){

        if (value.equals("All")){

            Iterable<Asana> asanas = asanaRepository.findAll();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("A")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeA();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("B")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeB();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("C")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeC();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("D")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeD();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("E")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeE();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("F")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeF();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("G")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeG();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("H")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeH();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("I")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeI();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("J")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeJ();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("K")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeK();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("L")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeL();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("M")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeM();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("N")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeN();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("O")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeO();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("P")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeP();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("Q")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeQ();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("R")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeR();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if(value.equals("S")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeS();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }else if (value.equals("T")){
            Iterable<Asana> asanas = asanaRepository.findAllByTitleLikeT();
            List<Asana> list = (List<Asana>) asanas;
            sort(list);
            model.addAttribute("asana", asanas);

        }

        return "index";
    }


    @GetMapping("/about-us")
    public String about(){
        return "about-us";
    }


    private static <E> void sort(List list){
        Collections.sort(list, new Comparator<Asana>(){

            @Override
            public int compare(final Asana o1, final Asana o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

    }

}
