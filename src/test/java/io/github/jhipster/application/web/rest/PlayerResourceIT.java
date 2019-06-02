package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JRiverApplicationApp;
import io.github.jhipster.application.domain.Player;
import io.github.jhipster.application.repository.PlayerRepository;
import io.github.jhipster.application.service.PlayerService;
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

import io.github.jhipster.application.domain.enumeration.PlayerState;
/**
 * Integration tests for the {@Link PlayerResource} REST controller.
 */
@SpringBootTest(classes = JRiverApplicationApp.class)
public class PlayerResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final PlayerState DEFAULT_STATE = PlayerState.EMPTY;
    private static final PlayerState UPDATED_STATE = PlayerState.RESERVE;

    private static final Boolean DEFAULT_DISCONNECT = false;
    private static final Boolean UPDATED_DISCONNECT = true;

    private static final Boolean DEFAULT_WANT_TO_LEAVE = false;
    private static final Boolean UPDATED_WANT_TO_LEAVE = true;

    private static final Integer DEFAULT_SAT_OUT_HANDS = 1;
    private static final Integer UPDATED_SAT_OUT_HANDS = 2;

    private static final Integer DEFAULT_SEAT_NUMBER = 1;
    private static final Integer UPDATED_SEAT_NUMBER = 2;

    private static final Integer DEFAULT_TABLE_REST = 1;
    private static final Integer UPDATED_TABLE_REST = 2;

    private static final Integer DEFAULT_HAND_REST = 1;
    private static final Integer UPDATED_HAND_REST = 2;

    private static final Integer DEFAULT_CHIP_IN_STAGE = 1;
    private static final Integer UPDATED_CHIP_IN_STAGE = 2;

    private static final String DEFAULT_CARDS = "AAAAAAAAAA";
    private static final String UPDATED_CARDS = "BBBBBBBBBB";

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

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

    private MockMvc restPlayerMockMvc;

    private Player player;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlayerResource playerResource = new PlayerResource(playerService);
        this.restPlayerMockMvc = MockMvcBuilders.standaloneSetup(playerResource)
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
    public static Player createEntity(EntityManager em) {
        Player player = new Player()
            .username(DEFAULT_USERNAME)
            .state(DEFAULT_STATE)
            .disconnect(DEFAULT_DISCONNECT)
            .wantToLeave(DEFAULT_WANT_TO_LEAVE)
            .satOutHands(DEFAULT_SAT_OUT_HANDS)
            .seatNumber(DEFAULT_SEAT_NUMBER)
            .tableRest(DEFAULT_TABLE_REST)
            .handRest(DEFAULT_HAND_REST)
            .chipInStage(DEFAULT_CHIP_IN_STAGE)
            .cards(DEFAULT_CARDS);
        return player;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Player createUpdatedEntity(EntityManager em) {
        Player player = new Player()
            .username(UPDATED_USERNAME)
            .state(UPDATED_STATE)
            .disconnect(UPDATED_DISCONNECT)
            .wantToLeave(UPDATED_WANT_TO_LEAVE)
            .satOutHands(UPDATED_SAT_OUT_HANDS)
            .seatNumber(UPDATED_SEAT_NUMBER)
            .tableRest(UPDATED_TABLE_REST)
            .handRest(UPDATED_HAND_REST)
            .chipInStage(UPDATED_CHIP_IN_STAGE)
            .cards(UPDATED_CARDS);
        return player;
    }

    @BeforeEach
    public void initTest() {
        player = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayer() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();

        // Create the Player
        restPlayerMockMvc.perform(post("/api/players")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(player)))
            .andExpect(status().isCreated());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeCreate + 1);
        Player testPlayer = playerList.get(playerList.size() - 1);
        assertThat(testPlayer.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testPlayer.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testPlayer.isDisconnect()).isEqualTo(DEFAULT_DISCONNECT);
        assertThat(testPlayer.isWantToLeave()).isEqualTo(DEFAULT_WANT_TO_LEAVE);
        assertThat(testPlayer.getSatOutHands()).isEqualTo(DEFAULT_SAT_OUT_HANDS);
        assertThat(testPlayer.getSeatNumber()).isEqualTo(DEFAULT_SEAT_NUMBER);
        assertThat(testPlayer.getTableRest()).isEqualTo(DEFAULT_TABLE_REST);
        assertThat(testPlayer.getHandRest()).isEqualTo(DEFAULT_HAND_REST);
        assertThat(testPlayer.getChipInStage()).isEqualTo(DEFAULT_CHIP_IN_STAGE);
        assertThat(testPlayer.getCards()).isEqualTo(DEFAULT_CARDS);
    }

    @Test
    @Transactional
    public void createPlayerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();

        // Create the Player with an existing ID
        player.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerMockMvc.perform(post("/api/players")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(player)))
            .andExpect(status().isBadRequest());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlayers() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        // Get all the playerList
        restPlayerMockMvc.perform(get("/api/players?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(player.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].disconnect").value(hasItem(DEFAULT_DISCONNECT.booleanValue())))
            .andExpect(jsonPath("$.[*].wantToLeave").value(hasItem(DEFAULT_WANT_TO_LEAVE.booleanValue())))
            .andExpect(jsonPath("$.[*].satOutHands").value(hasItem(DEFAULT_SAT_OUT_HANDS)))
            .andExpect(jsonPath("$.[*].seatNumber").value(hasItem(DEFAULT_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].tableRest").value(hasItem(DEFAULT_TABLE_REST)))
            .andExpect(jsonPath("$.[*].handRest").value(hasItem(DEFAULT_HAND_REST)))
            .andExpect(jsonPath("$.[*].chipInStage").value(hasItem(DEFAULT_CHIP_IN_STAGE)))
            .andExpect(jsonPath("$.[*].cards").value(hasItem(DEFAULT_CARDS.toString())));
    }
    
    @Test
    @Transactional
    public void getPlayer() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", player.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(player.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.disconnect").value(DEFAULT_DISCONNECT.booleanValue()))
            .andExpect(jsonPath("$.wantToLeave").value(DEFAULT_WANT_TO_LEAVE.booleanValue()))
            .andExpect(jsonPath("$.satOutHands").value(DEFAULT_SAT_OUT_HANDS))
            .andExpect(jsonPath("$.seatNumber").value(DEFAULT_SEAT_NUMBER))
            .andExpect(jsonPath("$.tableRest").value(DEFAULT_TABLE_REST))
            .andExpect(jsonPath("$.handRest").value(DEFAULT_HAND_REST))
            .andExpect(jsonPath("$.chipInStage").value(DEFAULT_CHIP_IN_STAGE))
            .andExpect(jsonPath("$.cards").value(DEFAULT_CARDS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlayer() throws Exception {
        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayer() throws Exception {
        // Initialize the database
        playerService.save(player);

        int databaseSizeBeforeUpdate = playerRepository.findAll().size();

        // Update the player
        Player updatedPlayer = playerRepository.findById(player.getId()).get();
        // Disconnect from session so that the updates on updatedPlayer are not directly saved in db
        em.detach(updatedPlayer);
        updatedPlayer
            .username(UPDATED_USERNAME)
            .state(UPDATED_STATE)
            .disconnect(UPDATED_DISCONNECT)
            .wantToLeave(UPDATED_WANT_TO_LEAVE)
            .satOutHands(UPDATED_SAT_OUT_HANDS)
            .seatNumber(UPDATED_SEAT_NUMBER)
            .tableRest(UPDATED_TABLE_REST)
            .handRest(UPDATED_HAND_REST)
            .chipInStage(UPDATED_CHIP_IN_STAGE)
            .cards(UPDATED_CARDS);

        restPlayerMockMvc.perform(put("/api/players")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlayer)))
            .andExpect(status().isOk());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
        Player testPlayer = playerList.get(playerList.size() - 1);
        assertThat(testPlayer.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testPlayer.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testPlayer.isDisconnect()).isEqualTo(UPDATED_DISCONNECT);
        assertThat(testPlayer.isWantToLeave()).isEqualTo(UPDATED_WANT_TO_LEAVE);
        assertThat(testPlayer.getSatOutHands()).isEqualTo(UPDATED_SAT_OUT_HANDS);
        assertThat(testPlayer.getSeatNumber()).isEqualTo(UPDATED_SEAT_NUMBER);
        assertThat(testPlayer.getTableRest()).isEqualTo(UPDATED_TABLE_REST);
        assertThat(testPlayer.getHandRest()).isEqualTo(UPDATED_HAND_REST);
        assertThat(testPlayer.getChipInStage()).isEqualTo(UPDATED_CHIP_IN_STAGE);
        assertThat(testPlayer.getCards()).isEqualTo(UPDATED_CARDS);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayer() throws Exception {
        int databaseSizeBeforeUpdate = playerRepository.findAll().size();

        // Create the Player

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerMockMvc.perform(put("/api/players")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(player)))
            .andExpect(status().isBadRequest());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayer() throws Exception {
        // Initialize the database
        playerService.save(player);

        int databaseSizeBeforeDelete = playerRepository.findAll().size();

        // Delete the player
        restPlayerMockMvc.perform(delete("/api/players/{id}", player.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Player.class);
        Player player1 = new Player();
        player1.setId(1L);
        Player player2 = new Player();
        player2.setId(player1.getId());
        assertThat(player1).isEqualTo(player2);
        player2.setId(2L);
        assertThat(player1).isNotEqualTo(player2);
        player1.setId(null);
        assertThat(player1).isNotEqualTo(player2);
    }
}
