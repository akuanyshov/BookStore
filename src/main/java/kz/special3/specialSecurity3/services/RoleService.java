package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Roles;

import java.util.List;

public interface RoleService  {
    List<Roles> getAllRole();
    Roles addRole(Roles role);
    Roles saveRole(Roles role);
    Roles getRole(Long id);
    Roles getByName(String name);
    void deleteRole(Roles roles);
}
