package com.example.pertemuan6.controller;

import com.example.pertemuan6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Menyimpan data sementara di list
    private List<User> dataMahasiswa = new ArrayList<>();

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
        model.addAttribute("mahasiswaList", dataMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute User user) {
        dataMahasiswa.add(user);
        return "redirect:/home";
    }
}