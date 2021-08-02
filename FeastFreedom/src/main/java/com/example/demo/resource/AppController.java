package com.example.demo.resource;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class AppController {

	@Autowired
	private UserService userservice; 
	
	@Autowired
	private MenuItemServiceImpl proservice; 
	
	@GetMapping("/user_homepage")
	public String userHome(Principal principal, Model model) {
		String email = principal.getName();
		User user = new User();
		List<User> list = userservice.listAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getEmail().equals(email)) {
				user = list.get(i);
				model.addAttribute("user", user);
				break;
			}
		}
		
		return "user_homepage";
	}
	
	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		userservice.save(user);
		System.out.println(user);
		return "register_success";
	}
	@RequestMapping("/order")
	public String view(@ModelAttribute("user") User user, Model model) {
		//List<MenuItem> listProducts = proservice();
		//model.addAttribute("listProducts", listProducts);
		//List<CartItem> list = userservice.getCart(user);
		//user.setCart(list);
		//model.addAttribute("user", user);
		//User user = userservice.get(1);
		//model.addAttribute("user", user);
		return "order";
	}
	

	@RequestMapping("/confirm")
	public String confirmorder(@ModelAttribute("user") User user, Model model) {
		//User user = userservice.get(1);
		//model.addAttribute("user", user);
		System.out.println(user);
		//List<ResUser> listUsers = service.listAll();
		//model.addAttribute("listUsers", listUsers);
		//List<Product> listProducts = proservice.listAll();
	//	model.addAttribute("listProducts", listProducts);
		//model.addAllAttributes("user", user);
		return "confirm";
	}
	
	@GetMapping("/viewcart")
	public String viewcart(@ModelAttribute("user") User user, Model model) {
		//User user = userservice.get(1);
		//model.addAttribute("user", user);
		//System.out.println("cart size: " + user.getCart().size());
		//System.out.println(user);
		//List<ResUser> listUsers = service.listAll();
		//model.addAttribute("listUsers", listUsers);
		//List<CartItem> list = userservice.getCart(user);
		//user.setCart(list);
		//model.addAttribute("items", list);
		//model.addAttribute("products", list);
		//model.addAllAttributes("user", user);
		return "viewcart";
	}
	
	
	/*@RequestMapping("/add/{id}")
	public String addDish(@PathVariable(name = "id") Long id,  Model model) {
		User user = (User) model.getAttribute("user");
		Product product = proservice.get(id);
		//model.addAttribute("product", product);	
		//User user = userservice.get(1);
		//model.addAttribute("user", user);
		System.out.println("user id: " + user.getId());
		System.out.println("user name:" + user.getName());
		//System.out.println(product.toString());
		CartItem item = proservice.convert(product);
		//System.out.println("item info: " + item.getName() + item.getBrand() + item.getMadein() + item.getPrice());
		//item.setUser(user);
		userservice.addToCart(user, item);
		System.out.println("added product");
		userservice.save(user);
		
		//List<ResUser> listUsers = service.listAll();
		//model.addAttribute("listUsers", listUsers);
		//List<Product> listProducts = proservice.listAll();
	//	model.addAttribute("listProducts", listProducts);
		//model.addAllAttributes("user", user);
		return "redirect:/order?added";
	}*/
	
	
	/*@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = proservice.get(id);
		mav.addObject("product", product);
		
		return mav;
	}*/


	
	

	
	/*

	
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = pservice.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "show";
	}
	/*
	
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
	*/
}
