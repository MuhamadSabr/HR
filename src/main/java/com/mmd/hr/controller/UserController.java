package com.mmd.hr.controller;


import com.mmd.hr.dto.user.UserDTO;
import com.mmd.hr.entity.authentication.User;
import com.mmd.hr.service.authentication.RoleService;
import com.mmd.hr.service.authentication.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private RoleService roleService;


	private final String userView = "authentication/user";
	private final String userFormView = "authentication/user-form";
	private final String userUpdateFormView = "authentication/user-update-form";
	private final String passwordUpdateFormView = "authentication/password-update-form";
	private final String redirectToUserPage = "redirect:/users/";

	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping("/")
	public String getUsers(Model model, HttpServletRequest request){
		List<User> users = userService.findAllUsers();
		users.sort(Comparator.comparing(User::getUsername));
		model.addAttribute("users", users);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return userView;
	}

	@GetMapping("/showUserForm")
	public String showUserForm(Model model, HttpServletRequest request){
		UserDTO userDTO = new UserDTO(new User(true), null);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		model.addAttribute("RolesList", roleService.findAllRoles());
		return userFormView;
	}

	@GetMapping("/showUpdateForm")
	String showUpdateForm(Model model, @RequestParam("username") String username, HttpServletRequest request){
		User user = userService.findUserByUsernameWithRoles(username);
		List<String> userRoles = new ArrayList<>();
		user.getRoles().forEach(role-> userRoles.add(role.getRole()));
		UserDTO userDTO = new UserDTO(user, userRoles);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("RolesList", roleService.findAllRoles());
		model.addAttribute("alreadySelectedRoles", userRoles);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return userUpdateFormView;
	}

	@PostMapping(value = "/saveUser")
	public String saveUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model, HttpServletRequest request){
		model.addAttribute("bindingResult: ", bindingResult);
		if(bindingResult.hasErrors()){
			model.addAttribute("RolesList", roleService.findAllRoles());
			model.addAttribute("alreadySelectedRoles", userDTO.getRolesNames());
			return userFormView;
		}
		userDTO.getUser().setRoles(roleService.findRolesByNames(userDTO.getRolesNames()));
		userService.save(userDTO.getUser());
		return redirectToUserPage;
	}

	@PostMapping(value = "/updateUser")
	public String updateUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model, HttpServletRequest request){
		model.addAttribute("bindingResult: ", bindingResult);
		if(bindingResult.hasErrors()){
			model.addAttribute("RolesList", roleService.findAllRoles());
			model.addAttribute("alreadySelectedRoles", userDTO.getRolesNames());
			return userUpdateFormView;
		}
		userDTO.getUser().setRoles(roleService.findRolesByNames(userDTO.getRolesNames()));
		userDTO.getUser().setPassword(userService.findUserByUsername(userDTO.getUser().getUsername()).getPassword());
		userDTO.getUser().setDateCreated(userService.findUserByUsername(userDTO.getUser().getUsername()).getDateCreated());
		userService.updateUser(userDTO.getUser());
		return redirectToUserPage;
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("username") String username, HttpServletRequest request){
		User user = userService.findUserByUsernameWithRoles(username);
		if(user!=null){
			user.setRoles(null);
			userService.delete(user);
		}
		return redirectToUserPage;
	}

	@GetMapping("/changePasswordForm")
	public String showChangePasswordForm(@RequestParam("username") String username, Model model, HttpServletRequest request){
		User user = userService.findUserByUsernameWithRoles(username);
		model.addAttribute("user", user);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		return passwordUpdateFormView;
	}

	@PostMapping(value = "/updatePassword")
	public String updateUserPassword(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest request){
		model.addAttribute("bindingResult: ", bindingResult);
		model.addAttribute("currentURI", "/"+request.getRequestURI().split("/")[1]+"/");
		if(bindingResult.hasErrors()){
			return passwordUpdateFormView;
		}
		User newUser = userService.findUserByUsernameWithRoles(user.getUsername());
		newUser.setPassword(user.getPassword());
		userService.updateUserPassword(newUser);
		return redirectToUserPage;
	}
}
