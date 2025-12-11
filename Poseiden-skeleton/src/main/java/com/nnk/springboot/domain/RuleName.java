package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_name_id")
    private Integer ruleNameId;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Json is mandatory")
    @Column(name = "json")
    private String json;

    @NotBlank(message = "Template is mandatory")
    @Column(name = "template")
    private String template;

    @NotBlank(message = "SQL is mandatory")
    @Column(name = "sqlStr")
    private String sql;

    @NotBlank(message = "SQL Part is mandatory")
    @Column(name = "sqlPart")
    private String sqlPart;

    public RuleName() {}

    public RuleName(String name,
                    String description,
                    String json,
                    String template,
                    String sql,
                    String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sql = sql;
        this.sqlPart = sqlPart;
    }

    public Integer getRuleNameId() {
        return ruleNameId;
    }

    public void setRuleNameId(Integer ruleNameId) {
        this.ruleNameId = ruleNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

    @Transient
    public Integer getId() {
        return ruleNameId;
    }

    public void setId(Integer id) {
        this.ruleNameId = id;
    }
}
