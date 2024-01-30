package org.lesson.projectwork.controller;

import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.MuseumUser;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.MuseumUserRepository;
import org.lesson.projectwork.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    MuseumUserRepository museumUserRepository;
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



}
