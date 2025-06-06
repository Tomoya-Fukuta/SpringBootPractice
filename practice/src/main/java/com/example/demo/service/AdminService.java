package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.form.AdminForm;

public interface AdminService {
	
	void registerAdmin(AdminForm adminForm);
    public UserDetails loadAdminByEmail(String email) throws UsernameNotFoundException;
}
