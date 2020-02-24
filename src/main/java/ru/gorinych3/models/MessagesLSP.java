package ru.gorinych3.models;

import javax.persistence.*;
import java.util.Date;


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getNewMessages",
                query = "EXECUTE getNewMessages",
                resultClass = MessagesLSP.class
        )
})
@Entity
public class MessagesLSP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "[InsertDate]", unique = true)
    private Date insertDate;

    @Column(name = "[LastProcessing]")
    private Date lastProcessing;

    @Column(name = "[Number]")
    private String number;

    @Column(name = "[Text]")
    private String text;

    @Column(name = "[Status]")
    private String status;

    @Column(name = "[State]")
    private int state;

    @Override
    public String toString() {
        return "MessagesLSP{" +
                "id=" + id +
                ", insertDate=" + insertDate +
                ", lastProcessing=" + lastProcessing +
                ", number='" + number + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", state=" + state +
                '}';
    }

    public long getId() {
        return id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public Date getLastProcessing() {
        return lastProcessing;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }

    public int getState() {
        return state;
    }

    public void setLastProcessing(Date lastProcessing) {
        this.lastProcessing = lastProcessing;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
