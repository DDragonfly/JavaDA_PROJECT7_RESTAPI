package com.nnk.springboot.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer curvePointId;

    public CurvePoint() {}

    public Integer getCurvePointId() {
        return curvePointId;
    }

    public void setCurvePointId(Integer curvePointId) {
        this.curvePointId = curvePointId;
    }
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
}
