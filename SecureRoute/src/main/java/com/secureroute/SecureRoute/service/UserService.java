package com.secureroute.SecureRoute.service;

import com.secureroute.SecureRoute.models.User;
import com.secureroute.SecureRoute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//gestiunea datelor care vin
//CRUD OPERATIONS PE USER READ, CREATE, UPDATE, DELETE
@Service
public class UserService {
    //dependency injection
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> readUser() {
        return userRepository.findAll();
    }

    public User readUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> readUsersByParentId(Long parentId) {
        List<User> allUsers = userRepository.findAll();

        List<User> usersWithParentId = allUsers.stream()
                .filter(user -> user.getParent_id() != null)
                .filter(user -> user.getParent_id().equals(parentId))
                .collect(Collectors.toList());

        return usersWithParentId;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User updateUser(User updatedUser) {
        User currentUser = userRepository.findById(updatedUser.getId()).get();
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());

        return userRepository.save(currentUser);
    }
}
