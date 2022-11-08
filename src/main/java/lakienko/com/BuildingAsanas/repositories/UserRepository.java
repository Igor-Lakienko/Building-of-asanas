package lakienko.com.BuildingAsanas.repositories;

import lakienko.com.BuildingAsanas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
