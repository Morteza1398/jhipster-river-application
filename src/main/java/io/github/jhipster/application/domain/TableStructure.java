package io.github.jhipster.application.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TableStructure.
 */
@Entity
@Table(name = "table_structure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TableStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "big_blind")
    private Integer bigBlind;

    @Column(name = "rake_policy")
    private String rakePolicy;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "min_buy_in")
    private Integer minBuyIn;

    @Column(name = "max_buy_in")
    private Integer maxBuyIn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBigBlind() {
        return bigBlind;
    }

    public TableStructure bigBlind(Integer bigBlind) {
        this.bigBlind = bigBlind;
        return this;
    }

    public void setBigBlind(Integer bigBlind) {
        this.bigBlind = bigBlind;
    }

    public String getRakePolicy() {
        return rakePolicy;
    }

    public TableStructure rakePolicy(String rakePolicy) {
        this.rakePolicy = rakePolicy;
        return this;
    }

    public void setRakePolicy(String rakePolicy) {
        this.rakePolicy = rakePolicy;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public TableStructure numberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getMinBuyIn() {
        return minBuyIn;
    }

    public TableStructure minBuyIn(Integer minBuyIn) {
        this.minBuyIn = minBuyIn;
        return this;
    }

    public void setMinBuyIn(Integer minBuyIn) {
        this.minBuyIn = minBuyIn;
    }

    public Integer getMaxBuyIn() {
        return maxBuyIn;
    }

    public TableStructure maxBuyIn(Integer maxBuyIn) {
        this.maxBuyIn = maxBuyIn;
        return this;
    }

    public void setMaxBuyIn(Integer maxBuyIn) {
        this.maxBuyIn = maxBuyIn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableStructure)) {
            return false;
        }
        return id != null && id.equals(((TableStructure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TableStructure{" +
            "id=" + getId() +
            ", bigBlind=" + getBigBlind() +
            ", rakePolicy='" + getRakePolicy() + "'" +
            ", numberOfSeats=" + getNumberOfSeats() +
            ", minBuyIn=" + getMinBuyIn() +
            ", maxBuyIn=" + getMaxBuyIn() +
            "}";
    }
}
