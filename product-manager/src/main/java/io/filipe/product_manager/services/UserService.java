package io.filipe.product_manager.services;

import io.filipe.product_manager.controllers.dto.UserDTO;
import io.filipe.product_manager.entities.User;
import io.filipe.product_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll(){
        List<User> user = userRepository.findAll();
        return user;
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public User insert(UserDTO userDTO){
        User user = new User();
        String passwordEncoded = passwordEncoder.encode(userDTO.getPassword());

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoded);

        userRepository.save(user);
        return user;
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User update(Long id, User newUser){
        User oldUser = userRepository.findById(id).orElseThrow();
        updateUser(oldUser, newUser);
        return userRepository.save(oldUser);
    }

    public void updateUser(User oldUser, User newUser){
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
