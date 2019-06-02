package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Pot;
import io.github.jhipster.application.service.PotService;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Pot}.
 */
@RestController
@RequestMapping("/api")
public class PotResource {

    private final Logger log = LoggerFactory.getLogger(PotResource.class);

    private static final String ENTITY_NAME = "pot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PotService potService;

    public PotResource(PotService potService) {
        this.potService = potService;
    }

    /**
     * {@code POST  /pots} : Create a new pot.
     *
     * @param pot the pot to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pot, or with status {@code 400 (Bad Request)} if the pot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pots")
    public ResponseEntity<Pot> createPot(@RequestBody Pot pot) throws URISyntaxException {
        log.debug("REST request to save Pot : {}", pot);
        if (pot.getId() != null) {
            throw new BadRequestAlertException("A new pot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pot result = potService.save(pot);
        return ResponseEntity.created(new URI("/api/pots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pots} : Updates an existing pot.
     *
     * @param pot the pot to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pot,
     * or with status {@code 400 (Bad Request)} if the pot is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pot couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pots")
    public ResponseEntity<Pot> updatePot(@RequestBody Pot pot) throws URISyntaxException {
        log.debug("REST request to update Pot : {}", pot);
        if (pot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pot result = potService.save(pot);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pot.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pots} : get all the pots.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pots in body.
     */
    @GetMapping("/pots")
    public List<Pot> getAllPots() {
        log.debug("REST request to get all Pots");
        return potService.findAll();
    }

    /**
     * {@code GET  /pots/:id} : get the "id" pot.
     *
     * @param id the id of the pot to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pot, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pots/{id}")
    public ResponseEntity<Pot> getPot(@PathVariable Long id) {
        log.debug("REST request to get Pot : {}", id);
        Optional<Pot> pot = potService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pot);
    }

    /**
     * {@code DELETE  /pots/:id} : delete the "id" pot.
     *
     * @param id the id of the pot to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pots/{id}")
    public ResponseEntity<Void> deletePot(@PathVariable Long id) {
        log.debug("REST request to delete Pot : {}", id);
        potService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
