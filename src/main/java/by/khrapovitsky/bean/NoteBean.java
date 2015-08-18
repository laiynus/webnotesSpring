package by.khrapovitsky.bean;

import by.khrapovitsky.dao.Factory;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
@SessionScoped
public class NoteBean {

    private int id;
    private String login;
    private String note;
    private Timestamp dateTimeCreate;
    private boolean isEdit = false;

    @ManagedProperty(value = "#{notes.lastNote}")
    List<Note> listNotes;

    @ManagedProperty(value = "#{notes.allNote}")
    List<Note> allNotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public List<Note> getListNotes() {
        User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
        List<Note> listNote = Factory.getInstance().getNotesDAO().getLastUserNotes(user);
        return listNote;
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public List<Note> getAllNotes() {
        User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
        List<Note> listNote = Factory.getInstance().getNotesDAO().getUserNotes(user);
        return listNote;
    }

    public void setAllNotes(List<Note> allNotes) {
        this.allNotes = allNotes;
    }

    public NoteBean() {
    }

    public void addNote() throws IOException {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin") == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
            Note note = new Note(user, this.note, new Timestamp(new java.util.Date().getTime()));
            Factory.getInstance().getNotesDAO().insert(note);
            this.isEdit = false;
            this.note = null;
            this.id = -1;
        }
    }

    public void removeNote(Note note) throws IOException {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin") == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            Note tmpNote = Factory.getInstance().getNotesDAO().getNoteWithUser(note.getId());
            if (tmpNote != null) {
                if (tmpNote.getUser().getLogin().equals(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"))) {
                    Factory.getInstance().getNotesDAO().delete(tmpNote);
                } else {
                    FacesMessage msg = new FacesMessage("Access error", "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } else {
                FacesMessage msg = new FacesMessage("This note is not found!", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void selectNote(Note tmp) {
        Note toEdit = Factory.getInstance().getNotesDAO().getNote(tmp.getId());
        if (toEdit != null) {
            this.isEdit = true;
            this.id = toEdit.getId();
            this.note = toEdit.getNote();
        }
    }

    public void editNote() throws IOException {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin") == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            Note tmpNote = Factory.getInstance().getNotesDAO().getNoteWithUser(id);
            if (tmpNote != null) {
                if (tmpNote.getUser().getLogin().equals(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"))) {
                    User user = new User((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin"), null);
                    Factory.getInstance().getNotesDAO().update(new Note(id, user, note, new Timestamp(new java.util.Date().getTime())));
                    isEdit = false;
                    note = null;
                    id = -1;
                } else {
                    FacesMessage msg = new FacesMessage("Access error", "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } else {
                FacesMessage msg = new FacesMessage("This note is not found!", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
}
