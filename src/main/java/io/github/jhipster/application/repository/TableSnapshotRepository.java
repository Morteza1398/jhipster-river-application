package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TableSnapshot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TableSnapshot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableSnapshotRepository extends JpaRepository<TableSnapshot, Long> {

}
