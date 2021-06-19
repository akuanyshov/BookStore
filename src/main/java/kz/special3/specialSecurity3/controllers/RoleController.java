package kz.special3.specialSecurity3.controllers;

import kz.special3.specialSecurity3.entities.Roles;
import kz.special3.specialSecurity3.entities.Users;
import kz.special3.specialSecurity3.repositories.UserRepository;
import kz.special3.specialSecurity3.services.RoleService;
import kz.special3.specialSecurity3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/adminRoles")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String adminRoles(Model model){
        List<Roles> allRoles=roleService.getAllRole();
        if(allRoles!=null){
            model.addAttribute("roles",allRoles);
        }
        return "adminRoles";
    }

    @PostMapping(value = "/addRole")
    public String addUser(Model model, RedirectAttributes attributes,
                          @RequestParam(name = "role_name") String name){
        Roles role=roleService.getByName(name);
        if(role==null){
            Roles new_role=new Roles();
            new_role.setRole(name);
            roleService.addRole(new_role);
            attributes.addFlashAttribute("successAddRole","Role was added successfully!!!");
        }else {
            attributes.addFlashAttribute("errorAddRole","Role with such name is existing!");
        }
        return "redirect:/adminRoles";
    }

    @GetMapping(value = "/detailRole/{id}")
    //@PreAuthorize("isAuthenticated()")
    public String detailRoles(Model model,
                              @PathVariable(name = "id") Long id) {
        Roles role = roleService.getRole(id);
        if (role != null) {
            model.addAttribute("role", role);
            model.addAttribute("currentUser", getUser());
        }
        return "/detailRole";
    }

    @PostMapping(value = "/editRole")
    public String editRole(Model model,RedirectAttributes attributes,
                           @RequestParam(name = "id") Long id,
                           @RequestParam(name = "role_name") String name){
        Roles role=roleService.getRole(id);
        if(role!=null){
            role.setRole(name);
            roleService.saveRole(role);
            attributes.addFlashAttribute("successEditRole","Role was edited successfully!!!");
        }else {
            attributes.addFlashAttribute("errorEditRole","Role wasn't edited!");
        }
        return "redirect:/adminRoles";
    }

    @PostMapping(value = "/deleteRole")
    public String deleteRole(RedirectAttributes attributes,@RequestParam(name = "id") Long id){
        Roles role=roleService.getRole(id);
        List<Users> allUsers=userRepository.findAll();
        if(role!=null){
            for (Users u:allUsers){
                for(int i=0;i<u.getRoles().size();i++){
                    if(u.getRoles().get(i)==role){
                        u.getRoles().remove(u.getRoles().get(i));
                        userRepository.save(u);
                    }
                }
            }
            roleService.deleteRole(role);
            attributes.addFlashAttribute("successDeleteRole","Role was deleted successfully!!!");
        }else {
            attributes.addFlashAttribute("errorDeleteRole","Role wasn't delete!");
        }
        return "redirect:/adminRoles";
    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }else{
            return null;
        }
    }
}
