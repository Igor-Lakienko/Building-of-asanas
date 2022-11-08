package lakienko.com.BuildingAsanas.repositories;

import lakienko.com.BuildingAsanas.models.User;
import lakienko.com.BuildingAsanas.models.UserAsanas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAsanasRepository extends JpaRepository<UserAsanas,Long> {

    public List<UserAsanas> findByUser(User user);



}
