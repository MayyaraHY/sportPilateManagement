package com.esprit.pilatespulse.pilatespulsev3.Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Event {

    private int eventID;
    private String name;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int nbrParticipants;
    private String description;
    private int coachID;
    private final ObservableSet<User> eventParticipants = FXCollections.observableSet();
    private final ObservableSet<Session> sessionList = FXCollections.observableSet();

    public Event() {
    }

    public Event(int eventID, String name, LocalDate startDate, LocalDate finishDate, int nbrParticipants, String description, int coachID) {
        this.eventID = eventID;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
    }

    public Event(String name, LocalDate startDate, LocalDate finishDate, int nbrParticipants, String description, int coachID) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
    }

    public Event(String name, LocalDate startDate, LocalDate finishDate, int nbrParticipants) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.nbrParticipants = nbrParticipants;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public ObservableSet<User> getEventParticipants() {
        return eventParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", nbrParticipants=" + nbrParticipants +
                ", description='" + description + '\'' +
                ", coachID=" + coachID +
                ", eventParticipants=" + eventParticipants +
                '}';
    }
}
