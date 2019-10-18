package drylo.tech.esoscrap.repo;

import drylo.tech.esoscrap.model.PowerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerDataRepo extends JpaRepository<PowerData, Long> {
}
