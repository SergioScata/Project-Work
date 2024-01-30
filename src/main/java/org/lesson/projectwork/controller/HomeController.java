package org.lesson.projectwork.controller;

import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.MuseumUserRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private MuseumUserRepository museumUserRepository;

    @GetMapping
    public String home(Model model) {
        List<Prodotto> prodottoList;
        prodottoList = prodottoRepository.findAll();
        model.addAttribute("prodottoList", prodottoList);
        return "home/home";
    }

}
