package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Roles;
import kz.special3.specialSecurity3.entities.Users;
import kz.special3.specialSecurity3.repositories.RoleRepository;
import kz.special3.specialSecurity3.repositories.UserRepository;
import kz.special3.specialSecurity3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users registerUser(Users user) {

        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){

            Roles userRole = roleRepository.findByRole("ROLE_USER");
            List<Roles> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

        }

        return null;

    }

    @Override
    public boolean updatePassword(Users user, String oldPassword,String newPassword){
        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(passwordEncoder.matches(oldPassword, checkUser.getPassword())){

            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user)!=null;

        }
        return false;
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
