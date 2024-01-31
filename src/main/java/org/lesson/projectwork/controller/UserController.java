package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.MuseumUser;
import org.lesson.projectwork.model.Role;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.MuseumUserRepository;
import org.lesson.projectwork.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    MuseumUserRepository museumUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping
    public String detail(Model model, Authentication authentication){
        DatabaseUserDetails loggedUser = (DatabaseUserDetails) authentication.getPrincipal();
        MuseumUser museumUser= museumUserRepository.findById(loggedUser.getId()).get();
        model.addAttribute("user", museumUser);
        return "user/detail";
    }
    @GetMapping("/acquisti")
    public String shop(Model model, Authentication authentication){
        DatabaseUserDetails loggedUser = (DatabaseUserDetails) authentication.getPrincipal();
        MuseumUser museumUser= museumUserRepository.findById(loggedUser.getId()).get();
        Set<Acquisto> acquisti= museumUser.getAcquisto();
        model.addAttribute("acquisti", acquisti);
        return "user/acquisti";
    }
    @GetMapping("/register")
    public String create(Model model) {
        MuseumUser user = new MuseumUser();
        model.addAttribute("user", user);
        return "home/register";
    }

    @PostMapping("/register")
    public String create2(@Valid @ModelAttribute("user") MuseumUser formUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", museumUserRepository.findAll());
            return "home/register";
        }
        Set<Role> userRole= new HashSet<>();
        Role userRole1= new Role();
        userRole.add(userRole1);
        userRole1.setName("USER");
        formUser.setPassword("{noop}"+formUser.getPassword());
        formUser.setRoleSet(userRole);
        MuseumUser savedUser = museumUserRepository.save(formUser);
        return "redirect:/login";
    }


}
