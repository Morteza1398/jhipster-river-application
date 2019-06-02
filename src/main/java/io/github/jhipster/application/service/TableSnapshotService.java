package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.TableSnapshot;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TableSnapshot}.
 */
public interface TableSnapshotService {

    /**
     * Save a tableSnapshot.
     *
     * @param tableSnapshot the entity to save.
     * @return the persisted entity.
     */
    TableSnapshot save(TableSnapshot tableSnapshot);

    /**
     * Get all the tableSnapshots.
     *
     * @return the list of entities.
     */
    List<TableSnapshot> findAll();


    /**
     * Get the "id" tableSnapshot.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableSnapshot> findOne(Long id);

    /**
     * Delete the "id" tableSnapshot.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
