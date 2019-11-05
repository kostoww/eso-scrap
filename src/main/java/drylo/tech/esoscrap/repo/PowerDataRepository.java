package drylo.tech.esoscrap.repo;

import drylo.tech.esoscrap.model.PowerGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerDataRepository extends JpaRepository<PowerGeneration, Long> {

}
