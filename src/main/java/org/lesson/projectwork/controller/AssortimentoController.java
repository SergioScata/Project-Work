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
    public String create(@RequestParam(name = "prodottoId", required = true) Integer prodottoId, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(prodottoId);
        if (result.isPresent()) {
            Prodotto prodottoToBuy = result.get();
            model.addAttribute("prodotto", prodottoToBuy);
            Assortimento newAssortimento = new Assortimento();

            model.addAttribute("assortimento", newAssortimento);
            return "assortimenti/create";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "prodotto with id " + prodottoId + " not found");
        }
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("assortimento") Assortimento formAssortimento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("prodotto", formAssortimento.getProdotto());
            return "assortimenti/create";
        }
        if (formAssortimento.getQuantitàAcquistata()>formAssortimento.getProdotto().getQuantità()){
            return "assortimenti/create";
        }
        formAssortimento.setDataAssortimento(LocalDate.now());
        formAssortimento.setPrezzoSingolo(formAssortimento.getProdotto().getPrezzo());
        formAssortimento.getProdotto().setQuantità(formAssortimento.getProdotto().getQuantità() + formAssortimento.getQuantitàAcquistata());
        BigDecimal newQuantità = BigDecimal.valueOf(formAssortimento.getQuantitàAcquistata());
        formAssortimento.setPrezzoTotale(formAssortimento.getPrezzoSingolo().multiply(newQuantità));
        Assortimento assortimentoToSave = assortimentoRepository.save(formAssortimento);
        return "redirect:/assortimenti";
    }
}
