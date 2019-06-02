package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TableStructure;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TableStructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableStructureRepository extends JpaRepository<TableStructure, Long> {

}
