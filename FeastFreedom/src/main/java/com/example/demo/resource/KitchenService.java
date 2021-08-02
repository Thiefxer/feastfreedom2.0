package com.example.demo.resource;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface KitchenService extends UserDetailsService {

	public List<Kitchen> getKitchenList();
	public Kitchen findKitchen(Long id);
	public void saveKitchen(Kitchen k);
	public void deleteKitchen(Long id);
	public Long getKitchenId(Kitchen k);
	public Kitchen save(KitchenRegistrationDto registration);
}
