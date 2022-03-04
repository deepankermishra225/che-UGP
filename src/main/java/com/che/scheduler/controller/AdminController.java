package com.che.scheduler.controller;

import com.che.scheduler.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService ;
    
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService ;
    }

    @GetMapping("/admin/all")
    public List<String> getAdmin() {
        return this.adminService.getAdmin() ;
    }

    @PostMapping("/admin/save")
    public void saveUsername(@RequestBody String username){
        this.adminService.saveUsername(username);
    }

    @DeleteMapping("/admin/delete/{username}")
    public void deleteUsername(@PathVariable String username){
        this.adminService.deleteUsername(username);
    }
}
