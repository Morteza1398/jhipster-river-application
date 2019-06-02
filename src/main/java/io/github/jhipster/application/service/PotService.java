package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Pot;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Pot}.
 */
public interface PotService {

    /**
     * Save a pot.
     *
     * @param pot the entity to save.
     * @return the persisted entity.
     */
    Pot save(Pot pot);

    /**
     * Get all the pots.
     *
     * @return the list of entities.
     */
    List<Pot> findAll();


    /**
     * Get the "id" pot.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pot> findOne(Long id);

    /**
     * Delete the "id" pot.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
