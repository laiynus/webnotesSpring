package by.khrapovitsky.service;

import by.khrapovitsky.model.User;

import java.util.List;

public interface UsersService {
    void delete(User user);
    void insert(User user);
    void update(User user);
    List<User> getAllUsers();
    User getUser(String login);
}
