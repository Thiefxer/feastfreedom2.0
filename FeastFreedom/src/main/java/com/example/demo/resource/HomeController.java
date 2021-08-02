package com.example.demo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	@Autowired
	KitchenServiceImpl kService;
	
	@Autowired
	private UserService userservice;
	
    @GetMapping("/")
    public String root() {
        return "index";
    }
    
    @GetMapping("/kitchenlogin")
    public String kitchenlogin(Model model) {
    	Kitchen k = new Kitchen();
    	model.addAttribute("kitchen", k);
    	//System.out.println("email: " + k.getEmail());
    	//System.out.println("pass: " + k.getPassword());
        return "kitchenlogin";
    }
    
    @PostMapping("/kitchenlogin")
    public String loginSubmit(@ModelAttribute("kitchenlogin") Kitchen k, Model model,
    		RedirectAttributes redirectAttributes) {
    	List<Kitchen> list = kService.getKitchenList();
    	for (int i = 0; i < list.size(); i++) {
    		if (list.get(i).equals(k)) {
    			redirectAttributes.addFlashAttribute("kitchen", list.get(i));
    			return "redirect:/edit_menu";
    		}
    	}	
    	return "redirect:/login?error";
    }
    
    @GetMapping("/userlogin")
    public String userlogin(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "userlogin";
	}
    @PostMapping("/userlogin")
    public String loginSubmit(@ModelAttribute("userlogin") User u, Model model,
    		RedirectAttributes redirectAttributes) {
    	List<User> list = userservice.listAll();
    	for (int i = 0; i < list.size(); i++) {
    		if (list.get(i).equals(u)) {
    			redirectAttributes.addFlashAttribute("user", list.get(i));
    			return "redirect:/user_homepage";
    		}
    	}	
    	return "redirect:/login?error";
    }
	
	@GetMapping("/new")
	public String registration(Model model) {
		//User user = new User();
		//model.addAttribute("user", user);	
		UserDto dto = new UserDto();
		model.addAttribute("user_dto", dto);
		return "new_user";
	}
	@PostMapping("/new")
	public String registrationSubmit(@Valid @ModelAttribute("user_dto") UserDto dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "new_user";
		else {
			userservice.save(dto);
			return "redirect:/?success";
		}
	}
	
    
}
