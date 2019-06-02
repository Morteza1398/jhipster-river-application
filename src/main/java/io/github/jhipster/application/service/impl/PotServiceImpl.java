package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PotService;
import io.github.jhipster.application.domain.Pot;
import io.github.jhipster.application.repository.PotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Pot}.
 */
@Service
@Transactional
public class PotServiceImpl implements PotService {

    private final Logger log = LoggerFactory.getLogger(PotServiceImpl.class);

    private final PotRepository potRepository;

    public PotServiceImpl(PotRepository potRepository) {
        this.potRepository = potRepository;
    }

    /**
     * Save a pot.
     *
     * @param pot the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Pot save(Pot pot) {
        log.debug("Request to save Pot : {}", pot);
        return potRepository.save(pot);
    }

    /**
     * Get all the pots.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Pot> findAll() {
        log.debug("Request to get all Pots");
        return potRepository.findAll();
    }


    /**
     * Get one pot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Pot> findOne(Long id) {
        log.debug("Request to get Pot : {}", id);
        return potRepository.findById(id);
    }

    /**
     * Delete the pot by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pot : {}", id);
        potRepository.deleteById(id);
    }
}
