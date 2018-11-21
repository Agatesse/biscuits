package com.agathevaisse.biscuits.domain;

import java.util.Objects;

public class Mission {

    private int id;
    private String action;
    private String imageURL;
    private boolean isDone;
    private int biscuitsToEarn;

    public Mission() {
    }

    public Mission(String action, int biscuitsToEarn) {
        this.action = action;
        this.imageURL = "src/main/biscuits-ui/src/assets/images/secret-mission-stamp.jpg";
        this.isDone = false;
        this.biscuitsToEarn = biscuitsToEarn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setimageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getBiscuitsToEarn() {
        return biscuitsToEarn;
    }

    public void setBiscuitsToEarn(int biscuitsToEarn) {
        this.biscuitsToEarn = biscuitsToEarn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mission)) return false;
        Mission mission = (Mission) o;
        return getId() == mission.getId() &&
                isDone() == mission.isDone() &&
                getBiscuitsToEarn() == mission.getBiscuitsToEarn() &&
                Objects.equals(getAction(), mission.getAction()) &&
                Objects.equals(getImageURL(), mission.getImageURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAction(), getImageURL(), isDone(), getBiscuitsToEarn());
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", image='" + imageURL + '\'' +
                ", isDone=" + isDone +
                ", biscuitsToEarn=" + biscuitsToEarn +
                '}';
    }

   public boolean switchDone() {
        if (this.isDone()) {
            this.setDone(false);
        } else {
            this.setDone(true);
        }
        return this.isDone();
    }
}
