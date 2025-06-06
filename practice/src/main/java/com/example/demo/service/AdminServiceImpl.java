package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerAdmin(AdminForm adminForm) {
		String encodePassword = passwordEncoder.encode(adminForm.getPassword());
		
		Admin admin = new Admin();
		
		admin.setLastName(adminForm.getLastName());
		admin.setFirstName(adminForm.getFirstName());
		admin.setEmail(adminForm.getEmail());
		admin.setPassword(encodePassword);
		
		adminRepository.save(admin);
	}

	@Override
    public UserDetails loadAdminByEmail(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("メールアドレスが見つかりません: " + email));

        return User.withUsername(admin.getEmail()) // ユーザー名にメールアドレスを使用
            .password(admin.getPassword()) // ハッシュ化されたパスワード
            .roles("ADMIN")
            .build();
    }
}
