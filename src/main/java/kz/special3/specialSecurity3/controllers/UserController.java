package kz.special3.specialSecurity3.controllers;

import kz.special3.specialSecurity3.entities.Roles;
import kz.special3.specialSecurity3.entities.Users;
import kz.special3.specialSecurity3.repositories.UserRepository;
import kz.special3.specialSecurity3.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "adminUsers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminUsers(Model model){
        List<Users> allUsers=userRepository.findAll();
        if(allUsers!=null){
            model.addAttribute("users",allUsers);
        }
        return "adminUsers";
    }

    @PostMapping(value = "/addUser")
    public String addUser(Model model, RedirectAttributes attributes,
                          @RequestParam(name = "user_email") String email,
                          @RequestParam(name = "user_password") String password,
                          @RequestParam(name = "user_name") String full_name){
        Users user=userRepository.findByEmail(email);
        if(user==null){
            Users new_user=new Users();
            new_user.setEmail(email);
            new_user.setPassword(passwordEncoder.encode(password));
            new_user.setFullName(full_name);
            userRepository.save(new_user);
            attributes.addFlashAttribute("successAddUser","User was added successfully!!!");
        }else {
            attributes.addFlashAttribute("errorAddUser","User with such email is existing!");
        }
        return "redirect:/adminUsers";
    }

    @GetMapping(value = "/detailUser/{email}")
    //@PreAuthorize("isAuthenticated()")
    public String detailCourses(Model model,
                                @PathVariable(name = "email") String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            List<Roles> allRoles=roleService.getAllRole();
            Roles role=roleService.getByName("ROLE_ADMIN");
            allRoles.remove(role);
            allRoles.removeAll(user.getRoles());
            model.addAttribute("not_roles",allRoles);
            model.addAttribute("user", user);
            model.addAttribute("currentUser", getUserData());
        }
        return "/detailUser";
    }

    @PostMapping(value = "/editUser")
    public String editCourse(Model model,RedirectAttributes attributes,
                             @RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_name") String name ){
        Users user=userRepository.findByEmail(email);
        if(user!=null){
            user.setFullName(name);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            attributes.addFlashAttribute("successEditUser","User was edited successfully!!!");
        }else {
            attributes.addFlashAttribute("errorEditUser","User wasn't edited!");
        }
        return "redirect:/adminUsers";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(RedirectAttributes attributes,@RequestParam(name = "email") String email){
        Users user=userRepository.findByEmail(email);
        if(user!=null){
            userRepository.delete(user);
            attributes.addFlashAttribute("successDeleteUser","User was deleted successfully!!!");
        }else {
            attributes.addFlashAttribute("errorDeleteUser","User wasn't delete!");
        }
        return "redirect:/adminUsers";
    }

    @PostMapping(value = "/minusRole")
    public String minusRole(Model model,
                            @RequestParam(name = "user_email") String email,
                            @RequestParam(name = "role_name") String name){
        Users user=userRepository.findByEmail(email);
        Roles role=roleService.getByName(name);
        if(user!=null && role!=null){
            List<Roles>us_roles=user.getRoles();
            if(us_roles!=null){
                us_roles.remove(role);
            }
            userRepository.save(user);
        }
        return "redirect:/detailUser/"+user.getEmail();
    }

    @PostMapping(value = "/plusRole")
    public String plusRole(Model model,
                           @RequestParam(name = "user_email") String email,
                           @RequestParam(name = "role_name") String name){
        Users user=userRepository.findByEmail(email);
        Roles role=roleService.getByName(name);
        if(user!=null && role!=null){
            List<Roles>us_roles=user.getRoles();
            if(us_roles==null){
                us_roles=new ArrayList<>();
            }
            us_roles.add(role);
            userRepository.save(user);
        }
        return "redirect:/detailUser/"+user.getEmail();
    }


    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }else{
            return null;
        }
    }
}
