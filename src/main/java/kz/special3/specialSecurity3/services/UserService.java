package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users registerUser(Users user);
    boolean updatePassword(Users user, String oldPassword, String newPassword);
    Users getUserByEmail(String email);
    List<Users> getAllUsers();
}
