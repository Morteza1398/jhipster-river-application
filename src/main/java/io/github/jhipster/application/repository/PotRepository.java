package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Pot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PotRepository extends JpaRepository<Pot, Long> {

}
