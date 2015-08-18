package by.khrapovitsky.bean;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class NavigationBean  implements Serializable {

    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/login.xhtml";
    }

    public String redirectToLastNotes() {
        return "/lastnotes.xhtml?faces-redirect=true";
    }

    public String toLastNotes() {
        return "/lastnotes.xhtml";
    }

    public String redirectToRegistration(){
        return  "/registration.xhtml?faces-redirect=true";
    }

    public String toRegistration(){
        return "/registration.xhtml";
    }

    public String redirectToAllNotes(){
        return "/allnotes.xhtml?faces-redirect=true";
    }

    public String toAllNotes(){
        return  "/allnotes.xhtml";
    }
}
