package lakienko.com.BuildingAsanas.service;

import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.UserAsanasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserAsanasServices{

    @Autowired
    private UserAsanasRepository userAsanasRepository;

    public List<UserAsanas> listUserAsanas (User user){
        return userAsanasRepository.findByUser(user);
    }

//    @Override
//    public Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
}
