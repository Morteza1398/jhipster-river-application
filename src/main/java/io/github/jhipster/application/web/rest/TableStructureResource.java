package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TableStructure;
import io.github.jhipster.application.service.TableStructureService;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TableStructure}.
 */
@RestController
@RequestMapping("/api")
public class TableStructureResource {

    private final Logger log = LoggerFactory.getLogger(TableStructureResource.class);

    private static final String ENTITY_NAME = "tableStructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableStructureService tableStructureService;

    public TableStructureResource(TableStructureService tableStructureService) {
        this.tableStructureService = tableStructureService;
    }

    /**
     * {@code POST  /table-structures} : Create a new tableStructure.
     *
     * @param tableStructure the tableStructure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableStructure, or with status {@code 400 (Bad Request)} if the tableStructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-structures")
    public ResponseEntity<TableStructure> createTableStructure(@RequestBody TableStructure tableStructure) throws URISyntaxException {
        log.debug("REST request to save TableStructure : {}", tableStructure);
        if (tableStructure.getId() != null) {
            throw new BadRequestAlertException("A new tableStructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableStructure result = tableStructureService.save(tableStructure);
        return ResponseEntity.created(new URI("/api/table-structures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-structures} : Updates an existing tableStructure.
     *
     * @param tableStructure the tableStructure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableStructure,
     * or with status {@code 400 (Bad Request)} if the tableStructure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableStructure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-structures")
    public ResponseEntity<TableStructure> updateTableStructure(@RequestBody TableStructure tableStructure) throws URISyntaxException {
        log.debug("REST request to update TableStructure : {}", tableStructure);
        if (tableStructure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TableStructure result = tableStructureService.save(tableStructure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tableStructure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /table-structures} : get all the tableStructures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableStructures in body.
     */
    @GetMapping("/table-structures")
    public List<TableStructure> getAllTableStructures() {
        log.debug("REST request to get all TableStructures");
        return tableStructureService.findAll();
    }

    /**
     * {@code GET  /table-structures/:id} : get the "id" tableStructure.
     *
     * @param id the id of the tableStructure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableStructure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-structures/{id}")
    public ResponseEntity<TableStructure> getTableStructure(@PathVariable Long id) {
        log.debug("REST request to get TableStructure : {}", id);
        Optional<TableStructure> tableStructure = tableStructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tableStructure);
    }

    /**
     * {@code DELETE  /table-structures/:id} : delete the "id" tableStructure.
     *
     * @param id the id of the tableStructure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-structures/{id}")
    public ResponseEntity<Void> deleteTableStructure(@PathVariable Long id) {
        log.debug("REST request to delete TableStructure : {}", id);
        tableStructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
