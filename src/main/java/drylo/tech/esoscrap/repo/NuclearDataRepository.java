package drylo.tech.esoscrap.repo;

import drylo.tech.esoscrap.model.NuclearGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NuclearDataRepository extends JpaRepository<NuclearGeneration, Long> {
}
