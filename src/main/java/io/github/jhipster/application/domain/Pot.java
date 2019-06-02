package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Pot.
 */
@Entity
@Table(name = "pot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pot_chips")
    private Integer potChips;

    @Column(name = "pot_players_seat_numbers")
    private String potPlayersSeatNumbers;

    @ManyToOne
    @JsonIgnoreProperties("pots")
    private TableSnapshot tableSnapshot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPotChips() {
        return potChips;
    }

    public Pot potChips(Integer potChips) {
        this.potChips = potChips;
        return this;
    }

    public void setPotChips(Integer potChips) {
        this.potChips = potChips;
    }

    public String getPotPlayersSeatNumbers() {
        return potPlayersSeatNumbers;
    }

    public Pot potPlayersSeatNumbers(String potPlayersSeatNumbers) {
        this.potPlayersSeatNumbers = potPlayersSeatNumbers;
        return this;
    }

    public void setPotPlayersSeatNumbers(String potPlayersSeatNumbers) {
        this.potPlayersSeatNumbers = potPlayersSeatNumbers;
    }

    public TableSnapshot getTableSnapshot() {
        return tableSnapshot;
    }

    public Pot tableSnapshot(TableSnapshot tableSnapshot) {
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
        if (!(o instanceof Pot)) {
            return false;
        }
        return id != null && id.equals(((Pot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pot{" +
            "id=" + getId() +
            ", potChips=" + getPotChips() +
            ", potPlayersSeatNumbers='" + getPotPlayersSeatNumbers() + "'" +
            "}";
    }
}
