package com.agathevaisse.biscuits.domain;

import java.util.Objects;

public class Mission {

    private int id;
    private String title;

    public Mission() {
    }

    public Mission(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mission)) return false;
        Mission mission = (Mission) o;
        return getId() == mission.getId() &&
                Objects.equals(getTitle(), mission.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
