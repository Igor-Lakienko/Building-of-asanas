package lakienko.com.BuildingAsanas;


import lakienko.com.BuildingAsanas.models.Asana;
import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import lakienko.com.BuildingAsanas.repositories.AsanaRepository;
import lakienko.com.BuildingAsanas.repositories.UserAsanasRepository;
import lakienko.com.BuildingAsanas.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CartsTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AsanaRepository asanaRepository;

    @Autowired
    private UserAsanasRepository userAsanasRepository;

    @Test
    public void testAddCartAsanas(){

        UserAsanas newUserAsana = new UserAsanas();
        newUserAsana.setUser(userRepository.findById(8L).orElse(new User()));
        newUserAsana.setAsana(asanaRepository.findById(1L).orElse(new Asana()));



        assert (newUserAsana.getId() > 0);

    }


}
