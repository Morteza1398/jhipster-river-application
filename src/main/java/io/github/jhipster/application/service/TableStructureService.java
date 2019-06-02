package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.TableStructure;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TableStructure}.
 */
public interface TableStructureService {

    /**
     * Save a tableStructure.
     *
     * @param tableStructure the entity to save.
     * @return the persisted entity.
     */
    TableStructure save(TableStructure tableStructure);

    /**
     * Get all the tableStructures.
     *
     * @return the list of entities.
     */
    List<TableStructure> findAll();


    /**
     * Get the "id" tableStructure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableStructure> findOne(Long id);

    /**
     * Delete the "id" tableStructure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
