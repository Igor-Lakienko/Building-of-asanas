package lia.ru.building.asanas.repository;

import lia.ru.building.asanas.models.Asana;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsanaRepository extends CrudRepository<Asana,Long> {

    @Query("FROM Asana u WHERE u.title LIKE 'А%'")
    Iterable<Asana> findAllByTitleLikeA();

    @Query("FROM Asana u WHERE u.title LIKE 'Б%'")
    Iterable<Asana> findAllByTitleLikeB();

    @Query("FROM Asana u WHERE u.title LIKE 'В%'")
    Iterable<Asana> findAllByTitleLikeC();

    @Query("FROM Asana u WHERE u.title LIKE 'Г%'")
    Iterable<Asana> findAllByTitleLikeD();

    @Query("FROM Asana u WHERE u.title LIKE 'Д%'")
    Iterable<Asana> findAllByTitleLikeE();

    @Query("FROM Asana u WHERE u.title LIKE 'И%'")
    Iterable<Asana> findAllByTitleLikeF();

    @Query("FROM Asana u WHERE u.title LIKE 'Й%'")
    Iterable<Asana> findAllByTitleLikeG();

    @Query("FROM Asana u WHERE u.title LIKE 'К%'")
    Iterable<Asana> findAllByTitleLikeH();

    @Query("FROM Asana u WHERE u.title LIKE 'Л%'")
    Iterable<Asana> findAllByTitleLikeI();

    @Query("FROM Asana u WHERE u.title LIKE 'М%'")
    Iterable<Asana> findAllByTitleLikeJ();

    @Query("FROM Asana u WHERE u.title LIKE 'Н%'")
    Iterable<Asana> findAllByTitleLikeK();

    @Query("FROM Asana u WHERE u.title LIKE 'П%'")
    Iterable<Asana> findAllByTitleLikeL();

    @Query("FROM Asana u WHERE u.title LIKE 'Р%'")
    Iterable<Asana> findAllByTitleLikeM();

    @Query("FROM Asana u WHERE u.title LIKE 'С%'")
    Iterable<Asana> findAllByTitleLikeN();

    @Query("FROM Asana u WHERE u.title LIKE 'Т%'")
    Iterable<Asana> findAllByTitleLikeO();

    @Query("FROM Asana u WHERE u.title LIKE 'У%'")
    Iterable<Asana> findAllByTitleLikeP();

    @Query("FROM Asana u WHERE u.title LIKE 'Х%'")
    Iterable<Asana> findAllByTitleLikeQ();

    @Query("FROM Asana u WHERE u.title LIKE 'Ч%'")
    Iterable<Asana> findAllByTitleLikeR();

    @Query("FROM Asana u WHERE u.title LIKE 'Ш%'")
    Iterable<Asana> findAllByTitleLikeS();

    @Query("FROM Asana u WHERE u.title LIKE 'Э%'")
    Iterable<Asana> findAllByTitleLikeT();

}
