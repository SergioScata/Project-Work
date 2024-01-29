package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/shop")
public class AcquistoController {
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {
        List<Prodotto> prodottoList;
        if (searchKeyword != null) {
            prodottoList = prodottoRepository.findByNomeContaining(searchKeyword);
        } else {
            prodottoList = prodottoRepository.findAll();
        }
        model.addAttribute("prodottoList", prodottoList);
        model.addAttribute("preloadSearch", searchKeyword);
        return "shop/list";
    }
    @GetMapping("/create")
    public String create(@RequestParam(name = "prodottoId", required = true) Integer prodottoId, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(prodottoId);
        if (result.isPresent()) {
            Prodotto prodottoToBuy = result.get();
            model.addAttribute("prodotto", prodottoToBuy);
            Acquisto newAcquisto = new Acquisto();
            newAcquisto.setProdotto(prodottoToBuy);


            model.addAttribute("acquisto", newAcquisto);
            return "shop/create";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "prodotto with id " + prodottoId + " not found");
        }
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("acquisto") Acquisto formAcquisto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("prodotto", formAcquisto.getProdotto());
            return "shop/create";
        }
        if (formAcquisto.getQuantita() > formAcquisto.getProdotto().getQuantita()) {
            model.addAttribute("prodotto", formAcquisto.getProdotto());
            model.addAttribute("errorMessage", "There is not enough quantity in stock.");

            return "shop/create";
        }
        formAcquisto.setDataAcquisto(LocalDate.now());
        formAcquisto.setPrezzoSingolo(formAcquisto.getProdotto().getPrezzo());
        Random random = new Random();
        int codiceRandom = (random.nextInt(100000, 999999));
        formAcquisto.setCodice(Integer.valueOf(codiceRandom));
        formAcquisto.setNome((formAcquisto.getProdotto().getNome()));
        formAcquisto.getProdotto().setQuantita(formAcquisto.getProdotto().getQuantita() - formAcquisto.getQuantita());
        BigDecimal newQuantità = BigDecimal.valueOf(formAcquisto.getQuantita());
        formAcquisto.setPrezzoTotale(formAcquisto.getPrezzoSingolo().multiply(newQuantità));
        Acquisto acquistoToSave = acquistoRepository.save(formAcquisto);


        return "redirect:/shop";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            Prodotto prodotto = result.get();
            model.addAttribute("prodotto", prodotto);
            return "shop/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }


}
