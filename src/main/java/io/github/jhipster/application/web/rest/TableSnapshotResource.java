package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TableSnapshot;
import io.github.jhipster.application.service.TableSnapshotService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.TableSnapshot}.
 */
@RestController
@RequestMapping("/api")
public class TableSnapshotResource {

    private final Logger log = LoggerFactory.getLogger(TableSnapshotResource.class);

    private static final String ENTITY_NAME = "tableSnapshot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableSnapshotService tableSnapshotService;

    public TableSnapshotResource(TableSnapshotService tableSnapshotService) {
        this.tableSnapshotService = tableSnapshotService;
    }

    /**
     * {@code POST  /table-snapshots} : Create a new tableSnapshot.
     *
     * @param tableSnapshot the tableSnapshot to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableSnapshot, or with status {@code 400 (Bad Request)} if the tableSnapshot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-snapshots")
    public ResponseEntity<TableSnapshot> createTableSnapshot(@RequestBody TableSnapshot tableSnapshot) throws URISyntaxException {
        log.debug("REST request to save TableSnapshot : {}", tableSnapshot);
        if (tableSnapshot.getId() != null) {
            throw new BadRequestAlertException("A new tableSnapshot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableSnapshot result = tableSnapshotService.save(tableSnapshot);
        return ResponseEntity.created(new URI("/api/table-snapshots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-snapshots} : Updates an existing tableSnapshot.
     *
     * @param tableSnapshot the tableSnapshot to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableSnapshot,
     * or with status {@code 400 (Bad Request)} if the tableSnapshot is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableSnapshot couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-snapshots")
    public ResponseEntity<TableSnapshot> updateTableSnapshot(@RequestBody TableSnapshot tableSnapshot) throws URISyntaxException {
        log.debug("REST request to update TableSnapshot : {}", tableSnapshot);
        if (tableSnapshot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TableSnapshot result = tableSnapshotService.save(tableSnapshot);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableSnapshot.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /table-snapshots} : get all the tableSnapshots.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableSnapshots in body.
     */
    @GetMapping("/table-snapshots")
    public List<TableSnapshot> getAllTableSnapshots() {
        log.debug("REST request to get all TableSnapshots");
        return tableSnapshotService.findAll();
    }

    /**
     * {@code GET  /table-snapshots/:id} : get the "id" tableSnapshot.
     *
     * @param id the id of the tableSnapshot to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableSnapshot, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-snapshots/{id}")
    public ResponseEntity<TableSnapshot> getTableSnapshot(@PathVariable Long id) {
        log.debug("REST request to get TableSnapshot : {}", id);
        Optional<TableSnapshot> tableSnapshot = tableSnapshotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableSnapshot);
    }

    /**
     * {@code DELETE  /table-snapshots/:id} : delete the "id" tableSnapshot.
     *
     * @param id the id of the tableSnapshot to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-snapshots/{id}")
    public ResponseEntity<Void> deleteTableSnapshot(@PathVariable Long id) {
        log.debug("REST request to delete TableSnapshot : {}", id);
        tableSnapshotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
