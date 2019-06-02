package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JRiverApplicationApp;
import io.github.jhipster.application.domain.TableStructure;
import io.github.jhipster.application.repository.TableStructureRepository;
import io.github.jhipster.application.service.TableStructureService;
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
 * Integration tests for the {@Link TableStructureResource} REST controller.
 */
@SpringBootTest(classes = JRiverApplicationApp.class)
public class TableStructureResourceIT {

    private static final Integer DEFAULT_BIG_BLIND = 1;
    private static final Integer UPDATED_BIG_BLIND = 2;

    private static final String DEFAULT_RAKE_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_RAKE_POLICY = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_SEATS = 1;
    private static final Integer UPDATED_NUMBER_OF_SEATS = 2;

    private static final Integer DEFAULT_MIN_BUY_IN = 1;
    private static final Integer UPDATED_MIN_BUY_IN = 2;

    private static final Integer DEFAULT_MAX_BUY_IN = 1;
    private static final Integer UPDATED_MAX_BUY_IN = 2;

    @Autowired
    private TableStructureRepository tableStructureRepository;

    @Autowired
    private TableStructureService tableStructureService;

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

    private MockMvc restTableStructureMockMvc;

    private TableStructure tableStructure;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TableStructureResource tableStructureResource = new TableStructureResource(tableStructureService);
        this.restTableStructureMockMvc = MockMvcBuilders.standaloneSetup(tableStructureResource)
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
    public static TableStructure createEntity(EntityManager em) {
        TableStructure tableStructure = new TableStructure()
            .bigBlind(DEFAULT_BIG_BLIND)
            .rakePolicy(DEFAULT_RAKE_POLICY)
            .numberOfSeats(DEFAULT_NUMBER_OF_SEATS)
            .minBuyIn(DEFAULT_MIN_BUY_IN)
            .maxBuyIn(DEFAULT_MAX_BUY_IN);
        return tableStructure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableStructure createUpdatedEntity(EntityManager em) {
        TableStructure tableStructure = new TableStructure()
            .bigBlind(UPDATED_BIG_BLIND)
            .rakePolicy(UPDATED_RAKE_POLICY)
            .numberOfSeats(UPDATED_NUMBER_OF_SEATS)
            .minBuyIn(UPDATED_MIN_BUY_IN)
            .maxBuyIn(UPDATED_MAX_BUY_IN);
        return tableStructure;
    }

    @BeforeEach
    public void initTest() {
        tableStructure = createEntity(em);
    }

    @Test
    @Transactional
    public void createTableStructure() throws Exception {
        int databaseSizeBeforeCreate = tableStructureRepository.findAll().size();

        // Create the TableStructure
        restTableStructureMockMvc.perform(post("/api/table-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableStructure)))
            .andExpect(status().isCreated());

        // Validate the TableStructure in the database
        List<TableStructure> tableStructureList = tableStructureRepository.findAll();
        assertThat(tableStructureList).hasSize(databaseSizeBeforeCreate + 1);
        TableStructure testTableStructure = tableStructureList.get(tableStructureList.size() - 1);
        assertThat(testTableStructure.getBigBlind()).isEqualTo(DEFAULT_BIG_BLIND);
        assertThat(testTableStructure.getRakePolicy()).isEqualTo(DEFAULT_RAKE_POLICY);
        assertThat(testTableStructure.getNumberOfSeats()).isEqualTo(DEFAULT_NUMBER_OF_SEATS);
        assertThat(testTableStructure.getMinBuyIn()).isEqualTo(DEFAULT_MIN_BUY_IN);
        assertThat(testTableStructure.getMaxBuyIn()).isEqualTo(DEFAULT_MAX_BUY_IN);
    }

    @Test
    @Transactional
    public void createTableStructureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tableStructureRepository.findAll().size();

        // Create the TableStructure with an existing ID
        tableStructure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableStructureMockMvc.perform(post("/api/table-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableStructure)))
            .andExpect(status().isBadRequest());

        // Validate the TableStructure in the database
        List<TableStructure> tableStructureList = tableStructureRepository.findAll();
        assertThat(tableStructureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTableStructures() throws Exception {
        // Initialize the database
        tableStructureRepository.saveAndFlush(tableStructure);

        // Get all the tableStructureList
        restTableStructureMockMvc.perform(get("/api/table-structures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableStructure.getId().intValue())))
            .andExpect(jsonPath("$.[*].bigBlind").value(hasItem(DEFAULT_BIG_BLIND)))
            .andExpect(jsonPath("$.[*].rakePolicy").value(hasItem(DEFAULT_RAKE_POLICY.toString())))
            .andExpect(jsonPath("$.[*].numberOfSeats").value(hasItem(DEFAULT_NUMBER_OF_SEATS)))
            .andExpect(jsonPath("$.[*].minBuyIn").value(hasItem(DEFAULT_MIN_BUY_IN)))
            .andExpect(jsonPath("$.[*].maxBuyIn").value(hasItem(DEFAULT_MAX_BUY_IN)));
    }
    
    @Test
    @Transactional
    public void getTableStructure() throws Exception {
        // Initialize the database
        tableStructureRepository.saveAndFlush(tableStructure);

        // Get the tableStructure
        restTableStructureMockMvc.perform(get("/api/table-structures/{id}", tableStructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tableStructure.getId().intValue()))
            .andExpect(jsonPath("$.bigBlind").value(DEFAULT_BIG_BLIND))
            .andExpect(jsonPath("$.rakePolicy").value(DEFAULT_RAKE_POLICY.toString()))
            .andExpect(jsonPath("$.numberOfSeats").value(DEFAULT_NUMBER_OF_SEATS))
            .andExpect(jsonPath("$.minBuyIn").value(DEFAULT_MIN_BUY_IN))
            .andExpect(jsonPath("$.maxBuyIn").value(DEFAULT_MAX_BUY_IN));
    }

    @Test
    @Transactional
    public void getNonExistingTableStructure() throws Exception {
        // Get the tableStructure
        restTableStructureMockMvc.perform(get("/api/table-structures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTableStructure() throws Exception {
        // Initialize the database
        tableStructureService.save(tableStructure);

        int databaseSizeBeforeUpdate = tableStructureRepository.findAll().size();

        // Update the tableStructure
        TableStructure updatedTableStructure = tableStructureRepository.findById(tableStructure.getId()).get();
        // Disconnect from session so that the updates on updatedTableStructure are not directly saved in db
        em.detach(updatedTableStructure);
        updatedTableStructure
            .bigBlind(UPDATED_BIG_BLIND)
            .rakePolicy(UPDATED_RAKE_POLICY)
            .numberOfSeats(UPDATED_NUMBER_OF_SEATS)
            .minBuyIn(UPDATED_MIN_BUY_IN)
            .maxBuyIn(UPDATED_MAX_BUY_IN);

        restTableStructureMockMvc.perform(put("/api/table-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTableStructure)))
            .andExpect(status().isOk());

        // Validate the TableStructure in the database
        List<TableStructure> tableStructureList = tableStructureRepository.findAll();
        assertThat(tableStructureList).hasSize(databaseSizeBeforeUpdate);
        TableStructure testTableStructure = tableStructureList.get(tableStructureList.size() - 1);
        assertThat(testTableStructure.getBigBlind()).isEqualTo(UPDATED_BIG_BLIND);
        assertThat(testTableStructure.getRakePolicy()).isEqualTo(UPDATED_RAKE_POLICY);
        assertThat(testTableStructure.getNumberOfSeats()).isEqualTo(UPDATED_NUMBER_OF_SEATS);
        assertThat(testTableStructure.getMinBuyIn()).isEqualTo(UPDATED_MIN_BUY_IN);
        assertThat(testTableStructure.getMaxBuyIn()).isEqualTo(UPDATED_MAX_BUY_IN);
    }

    @Test
    @Transactional
    public void updateNonExistingTableStructure() throws Exception {
        int databaseSizeBeforeUpdate = tableStructureRepository.findAll().size();

        // Create the TableStructure

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableStructureMockMvc.perform(put("/api/table-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableStructure)))
            .andExpect(status().isBadRequest());

        // Validate the TableStructure in the database
        List<TableStructure> tableStructureList = tableStructureRepository.findAll();
        assertThat(tableStructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTableStructure() throws Exception {
        // Initialize the database
        tableStructureService.save(tableStructure);

        int databaseSizeBeforeDelete = tableStructureRepository.findAll().size();

        // Delete the tableStructure
        restTableStructureMockMvc.perform(delete("/api/table-structures/{id}", tableStructure.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<TableStructure> tableStructureList = tableStructureRepository.findAll();
        assertThat(tableStructureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableStructure.class);
        TableStructure tableStructure1 = new TableStructure();
        tableStructure1.setId(1L);
        TableStructure tableStructure2 = new TableStructure();
        tableStructure2.setId(tableStructure1.getId());
        assertThat(tableStructure1).isEqualTo(tableStructure2);
        tableStructure2.setId(2L);
        assertThat(tableStructure1).isNotEqualTo(tableStructure2);
        tableStructure1.setId(null);
        assertThat(tableStructure1).isNotEqualTo(tableStructure2);
    }
}
