package org.lesson.projectwork.controller;

import org.lesson.projectwork.comparators.ProdottoAcquistoComparator;
import org.lesson.projectwork.model.Acquisto;
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

import java.util.*;

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
        List<Prodotto> prodottoList=prodottoRepository.findAll();

        List<Prodotto> topProdotti = new ArrayList<>();
        Comparator comparator= new ProdottoAcquistoComparator();

        Collections.sort(prodottoList,Collections.reverseOrder(comparator));
        topProdotti=prodottoList.subList(0,3);


        model.addAttribute("prodottiTop", topProdotti);



        return "home/home";
    }


}
