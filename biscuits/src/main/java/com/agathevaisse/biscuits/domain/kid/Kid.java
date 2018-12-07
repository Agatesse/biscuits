package com.agathevaisse.biscuits.domain.kid;

import java.util.Objects;

public class Kid {
    private Long id;
    private String nickname;
    private String imageURL;
    private int biscuitsEarned;

    public Kid() {
    }

    public Kid(String nickname) {
        this.nickname = nickname;
        this.imageURL = "imageURL";
        this.biscuitsEarned = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL() {
        this.imageURL = "imageURL";
    }

    public int getBiscuitsEarned() {
        return biscuitsEarned;
    }

    public void setBiscuitsEarned(int biscuitsEarned) {
        this.biscuitsEarned = biscuitsEarned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kid)) return false;
        Kid kid = (Kid) o;
        return getBiscuitsEarned() == kid.getBiscuitsEarned() &&
                Objects.equals(getId(), kid.getId()) &&
                Objects.equals(getNickname(), kid.getNickname()) &&
                Objects.equals(getImageURL(), kid.getImageURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNickname(), getImageURL(), getBiscuitsEarned());
    }

    @Override
    public String toString() {
        return "Kid{" +
                "id=" + id +
                ", nickName='" + nickname + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", biscuitsEarned=" + biscuitsEarned +
                '}';
    }
}
