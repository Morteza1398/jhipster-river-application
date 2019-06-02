package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.TableSnapshotService;
import io.github.jhipster.application.domain.TableSnapshot;
import io.github.jhipster.application.repository.TableSnapshotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TableSnapshot}.
 */
@Service
@Transactional
public class TableSnapshotServiceImpl implements TableSnapshotService {

    private final Logger log = LoggerFactory.getLogger(TableSnapshotServiceImpl.class);

    private final TableSnapshotRepository tableSnapshotRepository;

    public TableSnapshotServiceImpl(TableSnapshotRepository tableSnapshotRepository) {
        this.tableSnapshotRepository = tableSnapshotRepository;
    }

    /**
     * Save a tableSnapshot.
     *
     * @param tableSnapshot the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TableSnapshot save(TableSnapshot tableSnapshot) {
        log.debug("Request to save TableSnapshot : {}", tableSnapshot);
        return tableSnapshotRepository.save(tableSnapshot);
    }

    /**
     * Get all the tableSnapshots.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TableSnapshot> findAll() {
        log.debug("Request to get all TableSnapshots");
        return tableSnapshotRepository.findAll();
    }


    /**
     * Get one tableSnapshot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TableSnapshot> findOne(Long id) {
        log.debug("Request to get TableSnapshot : {}", id);
        return tableSnapshotRepository.findById(id);
    }

    /**
     * Delete the tableSnapshot by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableSnapshot : {}", id);
        tableSnapshotRepository.deleteById(id);
    }
}
