package com.example.demo.resource;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	@Lazy
    private BCryptPasswordEncoder userpasswordEncoder;
	
	public List<User> listAll() {
		return repo.findAll();
	}
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public User save(UserDto registration) {
		User user = new User();
        user.setName(registration.getName());
        user.setEmail(registration.getEmail());
        user.setPhone(registration.getPhone());
        user.setPassword(userpasswordEncoder.encode(registration.getPassword()));
        return repo.save(user);
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email);
        if(user ==null)
        	throw new UsernameNotFoundException("User 404");
		return new UserPrinciple(user);
	}
	
}
