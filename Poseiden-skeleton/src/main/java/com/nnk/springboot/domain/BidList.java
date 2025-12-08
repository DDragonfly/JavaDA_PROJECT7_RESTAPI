package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidListId;

    public BidList() {}

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListiId) {
        this.bidListId = bidListId;
    }
    // TODO: Map columns in data table BIDLIST with corresponding java fields
}
