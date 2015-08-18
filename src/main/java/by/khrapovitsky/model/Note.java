package by.khrapovitsky.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true,nullable = false)
    private int id;
    @Column(name = "note",nullable = false)
    private String note;
    @Column(name = "datetimecreate",nullable = false)
    private Timestamp dateTimeCreate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "login")
    private User user;

    public int getId() {
        return id;
    }



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(Timestamp dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Note(int id, User user, String note, Timestamp dateTimeCreate) {
        this.id = id;
        this.user = user;
        this.note = note;
        this.dateTimeCreate = dateTimeCreate;
    }

    public Note(User user, String note, Timestamp dateTimeCreate) {
        this.user = user;
        this.note = note;
        this.dateTimeCreate = dateTimeCreate;
    }

    public Note() {
    }
}
