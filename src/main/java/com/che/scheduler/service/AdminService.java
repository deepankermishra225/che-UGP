package com.che.scheduler.service;

import com.che.scheduler.models.Admin;
import com.che.scheduler.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository repository ;

    @Autowired
    public AdminService(AdminRepository repository){
        this.repository = repository ;
    }

    public void saveUsername(String username){
        if(!this.repository.existsByUsername(username)){
            Admin admin = new Admin() ;
            admin.setUsername(username);
            this.repository.save(admin) ;
        }
    }

    public List<String> getAdmin(){
        List<Admin> admin =  this.repository.findAll() ;
        List<String> adminString = new ArrayList<>() ;
        admin.forEach(a -> {
            adminString.add(a.getUsername()) ;
        });
        return adminString ;
    }

    public Boolean checkIfAdmin(String username){
        if(username.equals("dugc_che")|| username.equals("dmishra")) return true ;
        return this.repository.existsByUsername(username) ;
    }

    public void deleteUsername(String username){
        this.repository.deleteById(username);
    }
}
