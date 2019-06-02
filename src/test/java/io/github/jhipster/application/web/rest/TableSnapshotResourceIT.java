package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JRiverApplicationApp;
import io.github.jhipster.application.domain.TableSnapshot;
import io.github.jhipster.application.repository.TableSnapshotRepository;
import io.github.jhipster.application.service.TableSnapshotService;
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

import io.github.jhipster.application.domain.enumeration.Stage;
/**
 * Integration tests for the {@Link TableSnapshotResource} REST controller.
 */
@SpringBootTest(classes = JRiverApplicationApp.class)
public class TableSnapshotResourceIT {

    private static final Integer DEFAULT_DEALER_SEAT_NUMBER = 1;
    private static final Integer UPDATED_DEALER_SEAT_NUMBER = 2;

    private static final Stage DEFAULT_STAGE = Stage.EMPTY;
    private static final Stage UPDATED_STAGE = Stage.NOT_STARTED;

    private static final Integer DEFAULT_MAX_RAISED_CHIP = 1;
    private static final Integer UPDATED_MAX_RAISED_CHIP = 2;

    private static final Integer DEFAULT_TURN_ACT_SEAT_NUMBER = 1;
    private static final Integer UPDATED_TURN_ACT_SEAT_NUMBER = 2;

    private static final Integer DEFAULT_LAST_TO_ACT_SEAT_NUMBER = 1;
    private static final Integer UPDATED_LAST_TO_ACT_SEAT_NUMBER = 2;

    private static final Integer DEFAULT_FULL_RAISER_SEAT_NUMBER = 1;
    private static final Integer UPDATED_FULL_RAISER_SEAT_NUMBER = 2;

    private static final Integer DEFAULT_FULL_RAISER_CHIP_DIFFERENCE = 1;
    private static final Integer UPDATED_FULL_RAISER_CHIP_DIFFERENCE = 2;

    private static final Integer DEFAULT_NORMAL_PLAYERS_COUNT = 1;
    private static final Integer UPDATED_NORMAL_PLAYERS_COUNT = 2;

    private static final String DEFAULT_CHIP_IN_STAGE_PATTERN = "AAAAAAAAAA";
    private static final String UPDATED_CHIP_IN_STAGE_PATTERN = "BBBBBBBBBB";

    private static final String DEFAULT_CARDS = "AAAAAAAAAA";
    private static final String UPDATED_CARDS = "BBBBBBBBBB";

    @Autowired
    private TableSnapshotRepository tableSnapshotRepository;

    @Autowired
    private TableSnapshotService tableSnapshotService;

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

    private MockMvc restTableSnapshotMockMvc;

    private TableSnapshot tableSnapshot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TableSnapshotResource tableSnapshotResource = new TableSnapshotResource(tableSnapshotService);
        this.restTableSnapshotMockMvc = MockMvcBuilders.standaloneSetup(tableSnapshotResource)
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
    public static TableSnapshot createEntity(EntityManager em) {
        TableSnapshot tableSnapshot = new TableSnapshot()
            .dealerSeatNumber(DEFAULT_DEALER_SEAT_NUMBER)
            .stage(DEFAULT_STAGE)
            .maxRaisedChip(DEFAULT_MAX_RAISED_CHIP)
            .turnActSeatNumber(DEFAULT_TURN_ACT_SEAT_NUMBER)
            .lastToActSeatNumber(DEFAULT_LAST_TO_ACT_SEAT_NUMBER)
            .fullRaiserSeatNumber(DEFAULT_FULL_RAISER_SEAT_NUMBER)
            .fullRaiserChipDifference(DEFAULT_FULL_RAISER_CHIP_DIFFERENCE)
            .normalPlayersCount(DEFAULT_NORMAL_PLAYERS_COUNT)
            .chipInStagePattern(DEFAULT_CHIP_IN_STAGE_PATTERN)
            .cards(DEFAULT_CARDS);
        return tableSnapshot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TableSnapshot createUpdatedEntity(EntityManager em) {
        TableSnapshot tableSnapshot = new TableSnapshot()
            .dealerSeatNumber(UPDATED_DEALER_SEAT_NUMBER)
            .stage(UPDATED_STAGE)
            .maxRaisedChip(UPDATED_MAX_RAISED_CHIP)
            .turnActSeatNumber(UPDATED_TURN_ACT_SEAT_NUMBER)
            .lastToActSeatNumber(UPDATED_LAST_TO_ACT_SEAT_NUMBER)
            .fullRaiserSeatNumber(UPDATED_FULL_RAISER_SEAT_NUMBER)
            .fullRaiserChipDifference(UPDATED_FULL_RAISER_CHIP_DIFFERENCE)
            .normalPlayersCount(UPDATED_NORMAL_PLAYERS_COUNT)
            .chipInStagePattern(UPDATED_CHIP_IN_STAGE_PATTERN)
            .cards(UPDATED_CARDS);
        return tableSnapshot;
    }

    @BeforeEach
    public void initTest() {
        tableSnapshot = createEntity(em);
    }

    @Test
    @Transactional
    public void createTableSnapshot() throws Exception {
        int databaseSizeBeforeCreate = tableSnapshotRepository.findAll().size();

        // Create the TableSnapshot
        restTableSnapshotMockMvc.perform(post("/api/table-snapshots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableSnapshot)))
            .andExpect(status().isCreated());

        // Validate the TableSnapshot in the database
        List<TableSnapshot> tableSnapshotList = tableSnapshotRepository.findAll();
        assertThat(tableSnapshotList).hasSize(databaseSizeBeforeCreate + 1);
        TableSnapshot testTableSnapshot = tableSnapshotList.get(tableSnapshotList.size() - 1);
        assertThat(testTableSnapshot.getDealerSeatNumber()).isEqualTo(DEFAULT_DEALER_SEAT_NUMBER);
        assertThat(testTableSnapshot.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testTableSnapshot.getMaxRaisedChip()).isEqualTo(DEFAULT_MAX_RAISED_CHIP);
        assertThat(testTableSnapshot.getTurnActSeatNumber()).isEqualTo(DEFAULT_TURN_ACT_SEAT_NUMBER);
        assertThat(testTableSnapshot.getLastToActSeatNumber()).isEqualTo(DEFAULT_LAST_TO_ACT_SEAT_NUMBER);
        assertThat(testTableSnapshot.getFullRaiserSeatNumber()).isEqualTo(DEFAULT_FULL_RAISER_SEAT_NUMBER);
        assertThat(testTableSnapshot.getFullRaiserChipDifference()).isEqualTo(DEFAULT_FULL_RAISER_CHIP_DIFFERENCE);
        assertThat(testTableSnapshot.getNormalPlayersCount()).isEqualTo(DEFAULT_NORMAL_PLAYERS_COUNT);
        assertThat(testTableSnapshot.getChipInStagePattern()).isEqualTo(DEFAULT_CHIP_IN_STAGE_PATTERN);
        assertThat(testTableSnapshot.getCards()).isEqualTo(DEFAULT_CARDS);
    }

    @Test
    @Transactional
    public void createTableSnapshotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tableSnapshotRepository.findAll().size();

        // Create the TableSnapshot with an existing ID
        tableSnapshot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTableSnapshotMockMvc.perform(post("/api/table-snapshots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableSnapshot)))
            .andExpect(status().isBadRequest());

        // Validate the TableSnapshot in the database
        List<TableSnapshot> tableSnapshotList = tableSnapshotRepository.findAll();
        assertThat(tableSnapshotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTableSnapshots() throws Exception {
        // Initialize the database
        tableSnapshotRepository.saveAndFlush(tableSnapshot);

        // Get all the tableSnapshotList
        restTableSnapshotMockMvc.perform(get("/api/table-snapshots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tableSnapshot.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealerSeatNumber").value(hasItem(DEFAULT_DEALER_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())))
            .andExpect(jsonPath("$.[*].maxRaisedChip").value(hasItem(DEFAULT_MAX_RAISED_CHIP)))
            .andExpect(jsonPath("$.[*].turnActSeatNumber").value(hasItem(DEFAULT_TURN_ACT_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].lastToActSeatNumber").value(hasItem(DEFAULT_LAST_TO_ACT_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].fullRaiserSeatNumber").value(hasItem(DEFAULT_FULL_RAISER_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].fullRaiserChipDifference").value(hasItem(DEFAULT_FULL_RAISER_CHIP_DIFFERENCE)))
            .andExpect(jsonPath("$.[*].normalPlayersCount").value(hasItem(DEFAULT_NORMAL_PLAYERS_COUNT)))
            .andExpect(jsonPath("$.[*].chipInStagePattern").value(hasItem(DEFAULT_CHIP_IN_STAGE_PATTERN.toString())))
            .andExpect(jsonPath("$.[*].cards").value(hasItem(DEFAULT_CARDS.toString())));
    }
    
    @Test
    @Transactional
    public void getTableSnapshot() throws Exception {
        // Initialize the database
        tableSnapshotRepository.saveAndFlush(tableSnapshot);

        // Get the tableSnapshot
        restTableSnapshotMockMvc.perform(get("/api/table-snapshots/{id}", tableSnapshot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tableSnapshot.getId().intValue()))
            .andExpect(jsonPath("$.dealerSeatNumber").value(DEFAULT_DEALER_SEAT_NUMBER))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()))
            .andExpect(jsonPath("$.maxRaisedChip").value(DEFAULT_MAX_RAISED_CHIP))
            .andExpect(jsonPath("$.turnActSeatNumber").value(DEFAULT_TURN_ACT_SEAT_NUMBER))
            .andExpect(jsonPath("$.lastToActSeatNumber").value(DEFAULT_LAST_TO_ACT_SEAT_NUMBER))
            .andExpect(jsonPath("$.fullRaiserSeatNumber").value(DEFAULT_FULL_RAISER_SEAT_NUMBER))
            .andExpect(jsonPath("$.fullRaiserChipDifference").value(DEFAULT_FULL_RAISER_CHIP_DIFFERENCE))
            .andExpect(jsonPath("$.normalPlayersCount").value(DEFAULT_NORMAL_PLAYERS_COUNT))
            .andExpect(jsonPath("$.chipInStagePattern").value(DEFAULT_CHIP_IN_STAGE_PATTERN.toString()))
            .andExpect(jsonPath("$.cards").value(DEFAULT_CARDS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTableSnapshot() throws Exception {
        // Get the tableSnapshot
        restTableSnapshotMockMvc.perform(get("/api/table-snapshots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTableSnapshot() throws Exception {
        // Initialize the database
        tableSnapshotService.save(tableSnapshot);

        int databaseSizeBeforeUpdate = tableSnapshotRepository.findAll().size();

        // Update the tableSnapshot
        TableSnapshot updatedTableSnapshot = tableSnapshotRepository.findById(tableSnapshot.getId()).get();
        // Disconnect from session so that the updates on updatedTableSnapshot are not directly saved in db
        em.detach(updatedTableSnapshot);
        updatedTableSnapshot
            .dealerSeatNumber(UPDATED_DEALER_SEAT_NUMBER)
            .stage(UPDATED_STAGE)
            .maxRaisedChip(UPDATED_MAX_RAISED_CHIP)
            .turnActSeatNumber(UPDATED_TURN_ACT_SEAT_NUMBER)
            .lastToActSeatNumber(UPDATED_LAST_TO_ACT_SEAT_NUMBER)
            .fullRaiserSeatNumber(UPDATED_FULL_RAISER_SEAT_NUMBER)
            .fullRaiserChipDifference(UPDATED_FULL_RAISER_CHIP_DIFFERENCE)
            .normalPlayersCount(UPDATED_NORMAL_PLAYERS_COUNT)
            .chipInStagePattern(UPDATED_CHIP_IN_STAGE_PATTERN)
            .cards(UPDATED_CARDS);

        restTableSnapshotMockMvc.perform(put("/api/table-snapshots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTableSnapshot)))
            .andExpect(status().isOk());

        // Validate the TableSnapshot in the database
        List<TableSnapshot> tableSnapshotList = tableSnapshotRepository.findAll();
        assertThat(tableSnapshotList).hasSize(databaseSizeBeforeUpdate);
        TableSnapshot testTableSnapshot = tableSnapshotList.get(tableSnapshotList.size() - 1);
        assertThat(testTableSnapshot.getDealerSeatNumber()).isEqualTo(UPDATED_DEALER_SEAT_NUMBER);
        assertThat(testTableSnapshot.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testTableSnapshot.getMaxRaisedChip()).isEqualTo(UPDATED_MAX_RAISED_CHIP);
        assertThat(testTableSnapshot.getTurnActSeatNumber()).isEqualTo(UPDATED_TURN_ACT_SEAT_NUMBER);
        assertThat(testTableSnapshot.getLastToActSeatNumber()).isEqualTo(UPDATED_LAST_TO_ACT_SEAT_NUMBER);
        assertThat(testTableSnapshot.getFullRaiserSeatNumber()).isEqualTo(UPDATED_FULL_RAISER_SEAT_NUMBER);
        assertThat(testTableSnapshot.getFullRaiserChipDifference()).isEqualTo(UPDATED_FULL_RAISER_CHIP_DIFFERENCE);
        assertThat(testTableSnapshot.getNormalPlayersCount()).isEqualTo(UPDATED_NORMAL_PLAYERS_COUNT);
        assertThat(testTableSnapshot.getChipInStagePattern()).isEqualTo(UPDATED_CHIP_IN_STAGE_PATTERN);
        assertThat(testTableSnapshot.getCards()).isEqualTo(UPDATED_CARDS);
    }

    @Test
    @Transactional
    public void updateNonExistingTableSnapshot() throws Exception {
        int databaseSizeBeforeUpdate = tableSnapshotRepository.findAll().size();

        // Create the TableSnapshot

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTableSnapshotMockMvc.perform(put("/api/table-snapshots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tableSnapshot)))
            .andExpect(status().isBadRequest());

        // Validate the TableSnapshot in the database
        List<TableSnapshot> tableSnapshotList = tableSnapshotRepository.findAll();
        assertThat(tableSnapshotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTableSnapshot() throws Exception {
        // Initialize the database
        tableSnapshotService.save(tableSnapshot);

        int databaseSizeBeforeDelete = tableSnapshotRepository.findAll().size();

        // Delete the tableSnapshot
        restTableSnapshotMockMvc.perform(delete("/api/table-snapshots/{id}", tableSnapshot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<TableSnapshot> tableSnapshotList = tableSnapshotRepository.findAll();
        assertThat(tableSnapshotList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableSnapshot.class);
        TableSnapshot tableSnapshot1 = new TableSnapshot();
        tableSnapshot1.setId(1L);
        TableSnapshot tableSnapshot2 = new TableSnapshot();
        tableSnapshot2.setId(tableSnapshot1.getId());
        assertThat(tableSnapshot1).isEqualTo(tableSnapshot2);
        tableSnapshot2.setId(2L);
        assertThat(tableSnapshot1).isNotEqualTo(tableSnapshot2);
        tableSnapshot1.setId(null);
        assertThat(tableSnapshot1).isNotEqualTo(tableSnapshot2);
    }
}
