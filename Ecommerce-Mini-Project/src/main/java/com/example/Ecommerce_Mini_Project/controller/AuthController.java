package com.example.Ecommerce_Mini_Project.controller;


import com.example.Ecommerce_Mini_Project.dto.UserDTO;
import com.example.Ecommerce_Mini_Project.mapper.UserMapper;
import com.example.Ecommerce_Mini_Project.model.User;
import com.example.Ecommerce_Mini_Project.service.CartService;
import com.example.Ecommerce_Mini_Project.service.UserService;
import com.example.Ecommerce_Mini_Project.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String defaultToSignup(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        User user = userService.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        User existingUser = userService.findByUsername(userDTO.getUsername());

        if (existingUser != null) {
            model.addAttribute("error", "Username already taken");
            return "signup";
        }

        System.out.println("DTO password: " + userDTO.getPassword());
        System.out.println("Confirm Password: " + userDTO.getConfirmPassword());

        User newUser = userMapper.toEntity(userDTO);
        System.out.println("Entity password after mapping: " + newUser.getPassword());
        userService.saveUser(newUser);
        redirectAttributes.addFlashAttribute("success", "Signup successful! Please login.");
        return "redirect:/login";

    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
