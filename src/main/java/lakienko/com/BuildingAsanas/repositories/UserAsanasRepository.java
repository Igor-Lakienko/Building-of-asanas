package lakienko.com.BuildingAsanas.repositories;

import lakienko.com.BuildingAsanas.models.UserAsanas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserAsanasRepository extends JpaRepository<UserAsanas,Long> {





}
