package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Assortimento;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AssortimentoRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/assortimenti")
public class AssortimentoController {
    @Autowired
    private AssortimentoRepository assortimentoRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping
    public String index(Model model) {
        List<Assortimento> assortimentoList;

        assortimentoList = assortimentoRepository.findAll();
        Collections.sort(assortimentoList, Comparator.comparing(Assortimento::getDataAssortimento));

        model.addAttribute("assortimentiList", assortimentoList);
        return "assortimenti/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Prodotto> prodottoList = prodottoRepository.findAll();

        Assortimento newAssortimento = new Assortimento();
        model.addAttribute("prodottoList", prodottoList);

        model.addAttribute("assortimento", newAssortimento);
        return "assortimenti/create";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("assortimento") Assortimento assortimento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Prodotto> prodottoList = prodottoRepository.findAll();
            model.addAttribute("prodottoList", prodottoList);

            return "assortimenti/create";
        }
        assortimento.setNome(assortimento.getProdotto().getNome());

        assortimento.setDataAssortimento(LocalDate.now());

        BigDecimal newQuantità = BigDecimal.valueOf(assortimento.getQuantitaAcquistata());

        Assortimento assortimentoToSave = assortimentoRepository.save(assortimento);
        return "redirect:/assortimenti";
    }
}
