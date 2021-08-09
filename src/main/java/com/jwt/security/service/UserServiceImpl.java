package com.jwt.security.service;

import com.jwt.security.domain.Role;
import com.jwt.security.domain.User;
import com.jwt.security.repository.RoleRepository;
import com.jwt.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null){
            log.error("User not found.");
            throw new UsernameNotFoundException("User not found.");
        }else {
            log.info("User found: {}", userName);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving user.");
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role.");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Add role to an user.");
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Find User.");
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Find all Users.");
        return userRepository.findAll();
    }
}
