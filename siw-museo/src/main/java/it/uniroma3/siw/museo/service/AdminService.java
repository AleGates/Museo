package it.uniroma3.siw.museo.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Admin;
import it.uniroma3.siw.museo.repository.AdminRepository;

@Service
public class AdminService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected AdminRepository adminRepository;
	
	@Transactional
	public Admin getCredentials(Long id) {
		Optional<Admin> result = this.adminRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Admin getCredentials(String username) {
		Optional<Admin> result = this.adminRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Admin saveCredentials(Admin credentials) {
        credentials.setRole(Admin.ADMIN_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.adminRepository.save(credentials);
    }
}