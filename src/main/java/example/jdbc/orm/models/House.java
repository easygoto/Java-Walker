package example.jdbc.orm.models;

import core.toolkit.DateUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class House {
    private BigInteger id;
    private String     name;
    private BigDecimal money;
    private Timestamp  releaseTime;
    private Timestamp  createdAt;
    private Timestamp  updatedAt;
    private Integer    status;
    private Integer    isDelete;

    public House() {
        Timestamp now = DateUtil.getTimestamp();
        this.createdAt = now;
        this.updatedAt = now;
        this.status = 0;
        this.isDelete = 0;
    }

    public House(Object name, Object money, Object releaseTime) {
        this();
        this.name = (String) name;
        this.money = (BigDecimal) money;
        this.releaseTime = (Timestamp) releaseTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = (BigInteger) id;
    }

    public String getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = (String) name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = (BigDecimal) money;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Object releaseTime) {
        this.releaseTime = (Timestamp) releaseTime;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = (Timestamp) createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = (Timestamp) updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = (Integer) status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = (Integer) isDelete;
    }

    @Override
    public String toString() {
        return "void{" +
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
