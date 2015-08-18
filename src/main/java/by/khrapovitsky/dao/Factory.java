package by.khrapovitsky.dao;


public class Factory {
    private static UsersDAO usersDAO = null;
    private static NotesDAO notesDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public UsersDAO getUsersDAO(){
        if (usersDAO == null){
            usersDAO = new UsersDAOImplement();
        }
        return usersDAO;
    }

    public NotesDAO getNotesDAO(){
        if (notesDAO == null){
            notesDAO = new NotesDAOImplement();
        }
        return notesDAO;
    }

}
