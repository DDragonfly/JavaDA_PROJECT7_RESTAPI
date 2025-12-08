package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ruleNameId;

    public RuleName() {}

    public Integer getRuleNameId() {
        return ruleNameId;
    }

    public void setRuleNameId(Integer ruleNameId) {
        this.ruleNameId = ruleNameId;
    }
    // TODO: Map columns in data table RULENAME with corresponding java fields
}
