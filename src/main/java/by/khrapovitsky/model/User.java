package by.khrapovitsky.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "login",unique = true,nullable = false)
    String login;
    @Column(name = "password",nullable = false)
    String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch=FetchType.LAZY)
    private List<Note> notes;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
