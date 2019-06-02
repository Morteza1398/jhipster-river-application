package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Stage;

/**
 * A TableSnapshot.
 */
@Entity
@Table(name = "table_snapshot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TableSnapshot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dealer_seat_number")
    private Integer dealerSeatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage")
    private Stage stage;

    @Column(name = "max_raised_chip")
    private Integer maxRaisedChip;

    @Column(name = "turn_act_seat_number")
    private Integer turnActSeatNumber;

    @Column(name = "last_to_act_seat_number")
    private Integer lastToActSeatNumber;

    @Column(name = "full_raiser_seat_number")
    private Integer fullRaiserSeatNumber;

    @Column(name = "full_raiser_chip_difference")
    private Integer fullRaiserChipDifference;

    @Column(name = "normal_players_count")
    private Integer normalPlayersCount;

    @Column(name = "chip_in_stage_pattern")
    private String chipInStagePattern;

    @Column(name = "cards")
    private String cards;

    @OneToMany(mappedBy = "tableSnapshot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pot> pots = new HashSet<>();

    @OneToMany(mappedBy = "tableSnapshot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Player> players = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("tableSnapshots")
    private TableStructure structure;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDealerSeatNumber() {
        return dealerSeatNumber;
    }

    public TableSnapshot dealerSeatNumber(Integer dealerSeatNumber) {
        this.dealerSeatNumber = dealerSeatNumber;
        return this;
    }

    public void setDealerSeatNumber(Integer dealerSeatNumber) {
        this.dealerSeatNumber = dealerSeatNumber;
    }

    public Stage getStage() {
        return stage;
    }

    public TableSnapshot stage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Integer getMaxRaisedChip() {
        return maxRaisedChip;
    }

    public TableSnapshot maxRaisedChip(Integer maxRaisedChip) {
        this.maxRaisedChip = maxRaisedChip;
        return this;
    }

    public void setMaxRaisedChip(Integer maxRaisedChip) {
        this.maxRaisedChip = maxRaisedChip;
    }

    public Integer getTurnActSeatNumber() {
        return turnActSeatNumber;
    }

    public TableSnapshot turnActSeatNumber(Integer turnActSeatNumber) {
        this.turnActSeatNumber = turnActSeatNumber;
        return this;
    }

    public void setTurnActSeatNumber(Integer turnActSeatNumber) {
        this.turnActSeatNumber = turnActSeatNumber;
    }

    public Integer getLastToActSeatNumber() {
        return lastToActSeatNumber;
    }

    public TableSnapshot lastToActSeatNumber(Integer lastToActSeatNumber) {
        this.lastToActSeatNumber = lastToActSeatNumber;
        return this;
    }

    public void setLastToActSeatNumber(Integer lastToActSeatNumber) {
        this.lastToActSeatNumber = lastToActSeatNumber;
    }

    public Integer getFullRaiserSeatNumber() {
        return fullRaiserSeatNumber;
    }

    public TableSnapshot fullRaiserSeatNumber(Integer fullRaiserSeatNumber) {
        this.fullRaiserSeatNumber = fullRaiserSeatNumber;
        return this;
    }

    public void setFullRaiserSeatNumber(Integer fullRaiserSeatNumber) {
        this.fullRaiserSeatNumber = fullRaiserSeatNumber;
    }

    public Integer getFullRaiserChipDifference() {
        return fullRaiserChipDifference;
    }

    public TableSnapshot fullRaiserChipDifference(Integer fullRaiserChipDifference) {
        this.fullRaiserChipDifference = fullRaiserChipDifference;
        return this;
    }

    public void setFullRaiserChipDifference(Integer fullRaiserChipDifference) {
        this.fullRaiserChipDifference = fullRaiserChipDifference;
    }

    public Integer getNormalPlayersCount() {
        return normalPlayersCount;
    }

    public TableSnapshot normalPlayersCount(Integer normalPlayersCount) {
        this.normalPlayersCount = normalPlayersCount;
        return this;
    }

    public void setNormalPlayersCount(Integer normalPlayersCount) {
        this.normalPlayersCount = normalPlayersCount;
    }

    public String getChipInStagePattern() {
        return chipInStagePattern;
    }

    public TableSnapshot chipInStagePattern(String chipInStagePattern) {
        this.chipInStagePattern = chipInStagePattern;
        return this;
    }

    public void setChipInStagePattern(String chipInStagePattern) {
        this.chipInStagePattern = chipInStagePattern;
    }

    public String getCards() {
        return cards;
    }

    public TableSnapshot cards(String cards) {
        this.cards = cards;
        return this;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Set<Pot> getPots() {
        return pots;
    }

    public TableSnapshot pots(Set<Pot> pots) {
        this.pots = pots;
        return this;
    }

    public TableSnapshot addPots(Pot pot) {
        this.pots.add(pot);
        pot.setTableSnapshot(this);
        return this;
    }

    public TableSnapshot removePots(Pot pot) {
        this.pots.remove(pot);
        pot.setTableSnapshot(null);
        return this;
    }

    public void setPots(Set<Pot> pots) {
        this.pots = pots;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public TableSnapshot players(Set<Player> players) {
        this.players = players;
        return this;
    }

    public TableSnapshot addPlayers(Player player) {
        this.players.add(player);
        player.setTableSnapshot(this);
        return this;
    }

    public TableSnapshot removePlayers(Player player) {
        this.players.remove(player);
        player.setTableSnapshot(null);
        return this;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public TableStructure getStructure() {
        return structure;
    }

    public TableSnapshot structure(TableStructure tableStructure) {
        this.structure = tableStructure;
        return this;
    }

    public void setStructure(TableStructure tableStructure) {
        this.structure = tableStructure;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableSnapshot)) {
            return false;
        }
        return id != null && id.equals(((TableSnapshot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TableSnapshot{" +
            "id=" + getId() +
            ", dealerSeatNumber=" + getDealerSeatNumber() +
            ", stage='" + getStage() + "'" +
            ", maxRaisedChip=" + getMaxRaisedChip() +
            ", turnActSeatNumber=" + getTurnActSeatNumber() +
            ", lastToActSeatNumber=" + getLastToActSeatNumber() +
            ", fullRaiserSeatNumber=" + getFullRaiserSeatNumber() +
            ", fullRaiserChipDifference=" + getFullRaiserChipDifference() +
            ", normalPlayersCount=" + getNormalPlayersCount() +
            ", chipInStagePattern='" + getChipInStagePattern() + "'" +
            ", cards='" + getCards() + "'" +
            "}";
    }
}
