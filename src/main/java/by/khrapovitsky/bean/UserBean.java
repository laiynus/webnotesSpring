package by.khrapovitsky.bean;


import by.khrapovitsky.dao.Factory;
import by.khrapovitsky.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
public class UserBean implements Serializable {

    String login;
    String password;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

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

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    public UserBean() {
    }

    public void logIn() throws IOException {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin")!=null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("lastnotes.xhtml");
        }else{
            User tmpUser = Factory.getInstance().getUsersDAO().getUser(login);
            if (tmpUser == null) {
                FacesMessage msg = new FacesMessage("User not found!", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                if (tmpUser.getLogin().equals(login) && (tmpUser.getPassword().equals(password))) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acptLogin", login);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("lastnotes.xhtml");
                } else {
                    FacesMessage msg = new FacesMessage("Incorrect password!", "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        }
    }

    public void registration() throws IOException {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("acptLogin")!=null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("lastnotes.xhtml");
        }else{
            User tmpUser = Factory.getInstance().getUsersDAO().getUser(login);
            if (tmpUser != null) {
                FacesMessage msg = new FacesMessage("This user already exist!", "ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Factory.getInstance().getUsersDAO().insert(new User(login, password));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acptLogin", login);
                FacesContext.getCurrentInstance().getExternalContext().redirect("lastnotes.xhtml");
            }
        }
    }

    public String logOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acptLogin", null);
        return navigationBean.redirectToLogin();
    }
}
