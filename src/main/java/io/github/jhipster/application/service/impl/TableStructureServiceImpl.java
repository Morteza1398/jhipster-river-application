package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TableStructureService;
import io.github.jhipster.application.domain.TableStructure;
import io.github.jhipster.application.repository.TableStructureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TableStructure}.
 */
@Service
@Transactional
public class TableStructureServiceImpl implements TableStructureService {

    private final Logger log = LoggerFactory.getLogger(TableStructureServiceImpl.class);

    private final TableStructureRepository tableStructureRepository;

    public TableStructureServiceImpl(TableStructureRepository tableStructureRepository) {
        this.tableStructureRepository = tableStructureRepository;
    }

    /**
     * Save a tableStructure.
     *
     * @param tableStructure the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TableStructure save(TableStructure tableStructure) {
        log.debug("Request to save TableStructure : {}", tableStructure);
        return tableStructureRepository.save(tableStructure);
    }

    /**
     * Get all the tableStructures.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TableStructure> findAll() {
        log.debug("Request to get all TableStructures");
        return tableStructureRepository.findAll();
    }


    /**
     * Get one tableStructure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TableStructure> findOne(Long id) {
        log.debug("Request to get TableStructure : {}", id);
        return tableStructureRepository.findById(id);
    }

    /**
     * Delete the tableStructure by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableStructure : {}", id);
        tableStructureRepository.deleteById(id);
    }
}
