package com.secureroute.SecureRoute.service;

import com.secureroute.SecureRoute.models.Role;
import com.secureroute.SecureRoute.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//gestiunea datelor care vin
//CRUD OPERATIONS PE USER READ, CREATE, UPDATE, DELETE
@Service
public class RoleService {
    //dependency injection
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> readRole() {
        return roleRepository.findAll();
    }

    public Role readRoleByName(String name) { return  roleRepository.findByName(name).get();}

    public Role readRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Role updateRole(Role updatedRole) {
        Role currentRole = roleRepository.findById(updatedRole.getId()).get();
        currentRole.setName(updatedRole.getName());
        return roleRepository.save(currentRole);
    }


}
