package rc.bootsecurity.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.User;


@Service
public class UserService {

    private PasswordService passwordService;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordService passwordService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public User getUser(Long id) throws Exception {
        Optional<User> userOptional =  userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new Exception("User not found");
        }
        return userOptional.get();
    }

    public User addUser(User user) {
        user.setPassword(passwordService.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public String getUsername(Long id) {
        User user = userRepository.findById(id).get();
        return user.getUsername();
    }

    public long getUserCount() {
        return userRepository.count();
    }
}
