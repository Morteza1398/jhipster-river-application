package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JRiverApplicationApp;
import io.github.jhipster.application.domain.Pot;
import io.github.jhipster.application.repository.PotRepository;
import io.github.jhipster.application.service.PotService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PotResource} REST controller.
 */
@SpringBootTest(classes = JRiverApplicationApp.class)
public class PotResourceIT {

    private static final Integer DEFAULT_POT_CHIPS = 1;
    private static final Integer UPDATED_POT_CHIPS = 2;

    private static final String DEFAULT_POT_PLAYERS_SEAT_NUMBERS = "AAAAAAAAAA";
    private static final String UPDATED_POT_PLAYERS_SEAT_NUMBERS = "BBBBBBBBBB";

    @Autowired
    private PotRepository potRepository;

    @Autowired
    private PotService potService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPotMockMvc;

    private Pot pot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PotResource potResource = new PotResource(potService);
        this.restPotMockMvc = MockMvcBuilders.standaloneSetup(potResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pot createEntity(EntityManager em) {
        Pot pot = new Pot()
            .potChips(DEFAULT_POT_CHIPS)
            .potPlayersSeatNumbers(DEFAULT_POT_PLAYERS_SEAT_NUMBERS);
        return pot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pot createUpdatedEntity(EntityManager em) {
        Pot pot = new Pot()
            .potChips(UPDATED_POT_CHIPS)
            .potPlayersSeatNumbers(UPDATED_POT_PLAYERS_SEAT_NUMBERS);
        return pot;
    }

    @BeforeEach
    public void initTest() {
        pot = createEntity(em);
    }

    @Test
    @Transactional
    public void createPot() throws Exception {
        int databaseSizeBeforeCreate = potRepository.findAll().size();

        // Create the Pot
        restPotMockMvc.perform(post("/api/pots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pot)))
            .andExpect(status().isCreated());

        // Validate the Pot in the database
        List<Pot> potList = potRepository.findAll();
        assertThat(potList).hasSize(databaseSizeBeforeCreate + 1);
        Pot testPot = potList.get(potList.size() - 1);
        assertThat(testPot.getPotChips()).isEqualTo(DEFAULT_POT_CHIPS);
        assertThat(testPot.getPotPlayersSeatNumbers()).isEqualTo(DEFAULT_POT_PLAYERS_SEAT_NUMBERS);
    }

    @Test
    @Transactional
    public void createPotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = potRepository.findAll().size();

        // Create the Pot with an existing ID
        pot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPotMockMvc.perform(post("/api/pots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pot)))
            .andExpect(status().isBadRequest());

        // Validate the Pot in the database
        List<Pot> potList = potRepository.findAll();
        assertThat(potList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPots() throws Exception {
        // Initialize the database
        potRepository.saveAndFlush(pot);

        // Get all the potList
        restPotMockMvc.perform(get("/api/pots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pot.getId().intValue())))
            .andExpect(jsonPath("$.[*].potChips").value(hasItem(DEFAULT_POT_CHIPS)))
            .andExpect(jsonPath("$.[*].potPlayersSeatNumbers").value(hasItem(DEFAULT_POT_PLAYERS_SEAT_NUMBERS.toString())));
    }
    
    @Test
    @Transactional
    public void getPot() throws Exception {
        // Initialize the database
        potRepository.saveAndFlush(pot);

        // Get the pot
        restPotMockMvc.perform(get("/api/pots/{id}", pot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pot.getId().intValue()))
            .andExpect(jsonPath("$.potChips").value(DEFAULT_POT_CHIPS))
            .andExpect(jsonPath("$.potPlayersSeatNumbers").value(DEFAULT_POT_PLAYERS_SEAT_NUMBERS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPot() throws Exception {
        // Get the pot
        restPotMockMvc.perform(get("/api/pots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePot() throws Exception {
        // Initialize the database
        potService.save(pot);

        int databaseSizeBeforeUpdate = potRepository.findAll().size();

        // Update the pot
        Pot updatedPot = potRepository.findById(pot.getId()).get();
        // Disconnect from session so that the updates on updatedPot are not directly saved in db
        em.detach(updatedPot);
        updatedPot
            .potChips(UPDATED_POT_CHIPS)
            .potPlayersSeatNumbers(UPDATED_POT_PLAYERS_SEAT_NUMBERS);

        restPotMockMvc.perform(put("/api/pots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPot)))
            .andExpect(status().isOk());

        // Validate the Pot in the database
        List<Pot> potList = potRepository.findAll();
        assertThat(potList).hasSize(databaseSizeBeforeUpdate);
        Pot testPot = potList.get(potList.size() - 1);
        assertThat(testPot.getPotChips()).isEqualTo(UPDATED_POT_CHIPS);
        assertThat(testPot.getPotPlayersSeatNumbers()).isEqualTo(UPDATED_POT_PLAYERS_SEAT_NUMBERS);
    }

    @Test
    @Transactional
    public void updateNonExistingPot() throws Exception {
        int databaseSizeBeforeUpdate = potRepository.findAll().size();

        // Create the Pot

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPotMockMvc.perform(put("/api/pots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pot)))
            .andExpect(status().isBadRequest());

        // Validate the Pot in the database
        List<Pot> potList = potRepository.findAll();
        assertThat(potList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePot() throws Exception {
        // Initialize the database
        potService.save(pot);

        int databaseSizeBeforeDelete = potRepository.findAll().size();

        // Delete the pot
        restPotMockMvc.perform(delete("/api/pots/{id}", pot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Pot> potList = potRepository.findAll();
        assertThat(potList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pot.class);
        Pot pot1 = new Pot();
        pot1.setId(1L);
        Pot pot2 = new Pot();
        pot2.setId(pot1.getId());
        assertThat(pot1).isEqualTo(pot2);
        pot2.setId(2L);
        assertThat(pot1).isNotEqualTo(pot2);
        pot1.setId(null);
        assertThat(pot1).isNotEqualTo(pot2);
    }
}
