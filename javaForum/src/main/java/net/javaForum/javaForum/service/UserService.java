package net.javaForum.javaForum.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.helper.ValidateHelper;
import net.javaForum.javaForum.model.Ad;
import net.javaForum.javaForum.model.Question;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.RoleRepo;
import net.javaForum.javaForum.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepo userRepository;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }

    public boolean createUser(User user) {


        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        addRoleToUser(user.getUsername(), "ROLE_USER");
        return true;
    }

    public User update(String username, User updated) {

        if (userRepository.existsByUsername(username)) {

            User user = userRepository.getByUsername(username);
            userRepository.deleteByUsername(user.getUsername());
            updated.setPassword(passwordEncoder.encode(updated.getPassword()));
            userRepository.save(updated);
            return userRepository.getByUsername(updated.getUsername());
        }
        return null;
    }

    public User getUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    public boolean delete(String username) {

        if (userRepository.findByUsername(username).isPresent()) {

            userRepository.deleteByUsername(username);
            return true;
        }

        return false;
    }

    public List<Question> getQuestionByEmail(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getQuestionList();
        }
        throw new Exception();
    }

    public List<Ad> getAdByEmail(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getAdList();
        }
        throw new Exception();
    }


    public Role saveRole(Role role) {
        log.info("Saving new role {} to DB", role.getName());
        return roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} ", roleName, username);
        Optional<User> user = userRepository.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.get().getRoles().add(role);
    }
}
