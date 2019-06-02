package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.PlayerState;

/**
 * A Player.
 */
@Entity
@Table(name = "player")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PlayerState state;

    @Column(name = "jhi_disconnect")
    private Boolean disconnect;

    @Column(name = "want_to_leave")
    private Boolean wantToLeave;

    @Column(name = "sat_out_hands")
    private Integer satOutHands;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "table_rest")
    private Integer tableRest;

    @Column(name = "hand_rest")
    private Integer handRest;

    @Column(name = "chip_in_stage")
    private Integer chipInStage;

    @Column(name = "cards")
    private String cards;

    @ManyToOne
    @JsonIgnoreProperties("players")
    private TableSnapshot tableSnapshot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Player username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerState getState() {
        return state;
    }

    public Player state(PlayerState state) {
        this.state = state;
        return this;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public Boolean isDisconnect() {
        return disconnect;
    }

    public Player disconnect(Boolean disconnect) {
        this.disconnect = disconnect;
        return this;
    }

    public void setDisconnect(Boolean disconnect) {
        this.disconnect = disconnect;
    }

    public Boolean isWantToLeave() {
        return wantToLeave;
    }

    public Player wantToLeave(Boolean wantToLeave) {
        this.wantToLeave = wantToLeave;
        return this;
    }

    public void setWantToLeave(Boolean wantToLeave) {
        this.wantToLeave = wantToLeave;
    }

    public Integer getSatOutHands() {
        return satOutHands;
    }

    public Player satOutHands(Integer satOutHands) {
        this.satOutHands = satOutHands;
        return this;
    }

    public void setSatOutHands(Integer satOutHands) {
        this.satOutHands = satOutHands;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Player seatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getTableRest() {
        return tableRest;
    }

    public Player tableRest(Integer tableRest) {
        this.tableRest = tableRest;
        return this;
    }

    public void setTableRest(Integer tableRest) {
        this.tableRest = tableRest;
    }

    public Integer getHandRest() {
        return handRest;
    }

    public Player handRest(Integer handRest) {
        this.handRest = handRest;
        return this;
    }

    public void setHandRest(Integer handRest) {
        this.handRest = handRest;
    }

    public Integer getChipInStage() {
        return chipInStage;
    }

    public Player chipInStage(Integer chipInStage) {
        this.chipInStage = chipInStage;
        return this;
    }

    public void setChipInStage(Integer chipInStage) {
        this.chipInStage = chipInStage;
    }

    public String getCards() {
        return cards;
    }

    public Player cards(String cards) {
        this.cards = cards;
        return this;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public TableSnapshot getTableSnapshot() {
        return tableSnapshot;
    }

    public Player tableSnapshot(TableSnapshot tableSnapshot) {
        this.tableSnapshot = tableSnapshot;
        return this;
    }

    public void setTableSnapshot(TableSnapshot tableSnapshot) {
        this.tableSnapshot = tableSnapshot;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        return id != null && id.equals(((Player) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", state='" + getState() + "'" +
            ", disconnect='" + isDisconnect() + "'" +
            ", wantToLeave='" + isWantToLeave() + "'" +
            ", satOutHands=" + getSatOutHands() +
            ", seatNumber=" + getSeatNumber() +
            ", tableRest=" + getTableRest() +
            ", handRest=" + getHandRest() +
            ", chipInStage=" + getChipInStage() +
            ", cards='" + getCards() + "'" +
            "}";
    }
}
