package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Roles;
import kz.special3.specialSecurity3.repositories.RoleRepository;
import kz.special3.specialSecurity3.services.RoleService;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository rolesRepository;

    @Override
    public List<Roles> getAllRole() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles addRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public Roles saveRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public Roles getRole(Long id) {
        return rolesRepository.getOne(id);
    }

    @Override
    public Roles getByName(String name) {
        return rolesRepository.findByRole(name);
    }

    @Override
    public void deleteRole(Roles role) {
        rolesRepository.delete(role);
    }
}
