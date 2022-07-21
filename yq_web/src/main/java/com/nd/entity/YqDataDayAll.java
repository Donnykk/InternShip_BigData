package com.nd.entity;

import java.io.Serializable;

public class YqDataDayAll implements Serializable {
    private String date;
    private Integer sumConfirmed;
    private Integer sumSuspected;
    private Integer sumCured;
    private Integer sumDead;
    private Integer tdConfirmed;
    private Integer incConfirmed;
    private Integer incDead;
    private Integer incCured;

    public YqDataDayAll() {
    }

    public YqDataDayAll(String date, Integer sumConfirmed, Integer sumSuspected, Integer sumCured, Integer sumDead, Integer tdConfirmed, Integer incConfirmed, Integer incDead, Integer incCured) {
        this.date = date;
        this.sumConfirmed = sumConfirmed;
        this.sumSuspected = sumSuspected;
        this.sumCured = sumCured;
        this.sumDead = sumDead;
        this.tdConfirmed = tdConfirmed;
        this.incConfirmed = incConfirmed;
        this.incDead = incDead;
        this.incCured = incCured;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSumConfirmed() {
        return sumConfirmed;
    }

    public void setSumConfirmed(Integer sumConfirmed) {
        this.sumConfirmed = sumConfirmed;
    }

    public Integer getSumSuspected() {
        return sumSuspected;
    }

    public void setSumSuspected(Integer sumSuspected) {
        this.sumSuspected = sumSuspected;
    }

    public Integer getSumCured() {
        return sumCured;
    }

    public void setSumCured(Integer sumCured) {
        this.sumCured = sumCured;
    }

    public Integer getSumDead() {
        return sumDead;
    }

    public void setSumDead(Integer sumDead) {
        this.sumDead = sumDead;
    }

    public Integer getTdConfirmed() {
        return tdConfirmed;
    }

    public void setTdConfirmed(Integer tdConfirmed) {
        this.tdConfirmed = tdConfirmed;
    }

    public Integer getIncConfirmed() {
        return incConfirmed;
    }

    public void setIncConfirmed(Integer incConfirmed) {
        this.incConfirmed = incConfirmed;
    }

    public Integer getIncDead() {
        return incDead;
    }

    public void setIncDead(Integer incDead) {
        this.incDead = incDead;
    }

    public Integer getIncCured() {
        return incCured;
    }

    public void setIncCured(Integer incCured) {
        this.incCured = incCured;
    }

    @Override
    public String toString() {
        return "YqDataDayAll{" +
                "date='" + date + '\'' +
                ", sumConfirmed=" + sumConfirmed +
                ", sumSuspected=" + sumSuspected +
                ", sumCured=" + sumCured +
                ", sumDead=" + sumDead +
                ", tdConfirmed=" + tdConfirmed +
                ", incConfirmed=" + incConfirmed +
                ", incDead=" + incDead +
                ", incCured=" + incCured +
                '}';
    }
}
