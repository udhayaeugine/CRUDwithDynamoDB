package service;

import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository repo = new UserRepository();

    public void create(User user) {
        repo.createUser(user);
    }

    public User read(String userId) {
        return repo.getUser(userId);
    }

    public void update(User user) {
        repo.updateUser(user);
    }

    public void delete(String userId) {
        repo.deleteUser(userId);
    }
}