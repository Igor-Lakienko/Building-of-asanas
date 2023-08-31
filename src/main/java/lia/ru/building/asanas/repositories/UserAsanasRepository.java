package lia.ru.building.asanas.repositories;

import lia.ru.building.asanas.models.UserAsanas;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAsanasRepository extends CrudRepository<UserAsanas,Long> {

    @Override
    @Modifying
    @Query("DELETE FROM UserAsanas u WHERE u.id = ?1")
    void deleteById(Long id);
}
