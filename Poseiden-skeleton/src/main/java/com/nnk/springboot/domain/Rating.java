package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Moodys rating is mandatory")
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "SandP rating is mandatory")
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "Fitch rating is mandatory")
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotNull(message = "Order is mandatory")
    @Positive(message = "Order must be positive")
    @Column(name = "orderNumber")
    private Integer orderNumber;

    public Rating() {}

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Transient
    public Integer getOrder() {
        return orderNumber;
    }

    public void setOrder(Integer order) {
        this.orderNumber = order;
    }

    // TODO: Map columns in data table RATING with corresponding java fields
}
