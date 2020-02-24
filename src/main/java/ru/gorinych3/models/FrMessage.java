package ru.gorinych3.models;

import javax.persistence.*;
import java.util.Date;

/**
 * данный объект предназначен для работы вне DAO
 * в данный объект преобразуются все @Entity, полученные из БД
 * набор свойств зависит исключительно от бизнес-логики и интерфейса стороннего клиента, которому эти данные
 * будут отправлены
 */

public class FrMessage {

    private long id;

    private Date lastprocess;

    private String phone;

    private String text;

    private int count;

    public int getCount() {
        return count;
    }

    public FrMessage(long id, Date lastprocess, String phone, String text, int count) {
        this.id = id;
        this.lastprocess = lastprocess;
        this.phone = phone;
        this.text = text;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLastprocess() {
        return lastprocess;
    }

    public void setLastprocess(Date lastprocess) {
        this.lastprocess = lastprocess;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "FrMessage{" +
                "id=" + id +
                ", lastprocess=" + lastprocess +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", count=" + count +
                '}';
    }
}
