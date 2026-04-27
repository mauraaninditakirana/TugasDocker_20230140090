package com.example.pertemuan6.controller;

import com.example.pertemuan6.model.User;
import com.example.pertemuan6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "20230140090".equals(password)) {
            return "redirect:/home";
        }
        model.addAttribute("error", "Username atau Password salah!");
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        // Ambil data dari database, bukan dari List
        model.addAttribute("mahasiswaList", userRepository.findAll());
        return "home";
    }

    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute User user) {
        // Simpan ke database, bukan ke List
        userRepository.save(user);
        return "redirect:/home";
    }
}