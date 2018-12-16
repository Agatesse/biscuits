package com.agathevaisse.biscuits.domain.mission;

import com.agathevaisse.biscuits.domain.kid.Kid;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Mission {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String action;
    private String imageURL;
    private boolean isDone;

    @NotBlank
    @Min(1)
    private int biscuitsToEarn;
    
    @NotBlank
    private Kid kid;

    public Mission() {
    }

    public Mission(String action, int biscuitsToEarn, Kid kid) {
        this.action = action;
        this.imageURL = "src/main/biscuits-ui/src/assets/images/secret-mission.jpg";
        this.isDone = false;
        this.biscuitsToEarn = biscuitsToEarn;
        this.kid = kid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setImageURL() {
        this.imageURL = "src/main/biscuits-ui/src/assets/images/secret-mission.jpg";
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

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mission)) return false;
        Mission mission = (Mission) o;
        return isDone() == mission.isDone() &&
                getBiscuitsToEarn() == mission.getBiscuitsToEarn() &&
                Objects.equals(getId(), mission.getId()) &&
                Objects.equals(getAction(), mission.getAction()) &&
                Objects.equals(getImageURL(), mission.getImageURL()) &&
                Objects.equals(getKid(), mission.getKid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAction(), getImageURL(), isDone(), getBiscuitsToEarn(), getKid());
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", isDone=" + isDone +
                ", biscuitsToEarn=" + biscuitsToEarn +
                ", kid=" + kid +
                '}';
    }
}
