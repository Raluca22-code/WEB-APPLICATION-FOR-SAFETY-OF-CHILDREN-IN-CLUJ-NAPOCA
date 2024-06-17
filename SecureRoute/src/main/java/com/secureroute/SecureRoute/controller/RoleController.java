package com.secureroute.SecureRoute.controller;

import com.secureroute.SecureRoute.models.Role;
import com.secureroute.SecureRoute.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/read")
    public List<Role> readRole() {
        return roleService.readRole();
    }

    @GetMapping("/read/{readId}")
    public Role readRoleById(@PathVariable Long readId) {
        return roleService.readRoleById(readId);
    }

    @PostMapping("/create")
    public Role createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }

    @DeleteMapping("/delete/{readId}")
    public void deleteRole(@PathVariable Long readId){
        roleService.deleteRole(readId);
    }

    @PutMapping("/update")
    public Role updateRole(@RequestBody Role updateRole){
        return roleService.updateRole(updateRole);
    }

}
