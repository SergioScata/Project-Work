package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Assortimento;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AssortimentoRepository;
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

@Controller
@RequestMapping("/assortimenti")
public class AssortimentoController {
    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;
    @GetMapping
    public String index( Model model) {
        List<Assortimento> assortimentoList;

        assortimentoList = assortimentoRepository.findAll();

        model.addAttribute("assortimentiList", assortimentoList);
        return "assortimenti/list";
    }

    @GetMapping("/create")
    public String create( Model model) {
        List<Prodotto>prodottoList=prodottoRepository.findAll();

            Assortimento newAssortimento = new Assortimento();
            model.addAttribute("prodottoList", prodottoList);

            model.addAttribute("assortimento", newAssortimento);
            return "assortimenti/create";

    }
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("assortimento") Assortimento assortimento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Prodotto>prodottoList=prodottoRepository.findAll();
            model.addAttribute("prodottoList", prodottoList);

            return "assortimenti/create";
        }

        assortimento.setDataAssortimento(LocalDate.now());
        assortimento.setPrezzoSingolo(assortimento.getProdotto().getPrezzo());
        assortimento.getProdotto().setQuantità(assortimento.getProdotto().getQuantità() + assortimento.getQuantitàAcquistata());
        BigDecimal newQuantità = BigDecimal.valueOf(assortimento.getQuantitàAcquistata());
        assortimento.setPrezzoTotale(assortimento.getPrezzoSingolo().multiply(newQuantità));
        Assortimento assortimentoToSave = assortimentoRepository.save(assortimento);
        return "redirect:/assortimenti";
    }
}
