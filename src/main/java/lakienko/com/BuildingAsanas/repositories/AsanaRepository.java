package lakienko.com.BuildingAsanas.repositories;

import lakienko.com.BuildingAsanas.models.Asana;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsanaRepository extends CrudRepository<Asana,Long> {
}
