package jdbc.app.domain;

import java.util.Calendar;
import java.util.Date;

public class House {
    private long    id;
    private String  name;
    private float   money;
    private Date    releaseTime;
    private Date    createdAt;
    private Date    updatedAt;
    private int     status;
    private boolean isDelete;

    public House() {
        Date now = Calendar.getInstance().getTime();
        this.createdAt = now;
        this.updatedAt = now;
        this.status = 0;
        this.isDelete = false;
    }

    public House(String name, float money, Date releaseTime) {
        this();
        this.name = name;
        this.money = money;
        this.releaseTime = releaseTime;
    }

    public long getId() {
        return id;
    }

    public House setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public House setName(String name) {
        this.name = name;
        return this;
    }

    public float getMoney() {
        return money;
    }

    public House setMoney(float money) {
        this.money = money;
        return this;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public House setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public House setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public House setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public House setDelete(boolean delete) {
        isDelete = delete;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public House setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", releaseTime=" + releaseTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", isDelete=" + isDelete +
                '}';
    }
}
