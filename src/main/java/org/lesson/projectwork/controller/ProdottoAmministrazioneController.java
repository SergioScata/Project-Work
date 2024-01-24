package org.lesson.projectwork.controller;

import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/shop/amministrazione")
public class ProdottoAmministrazioneController {
    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            Prodotto prodotto = result.get();
            model.addAttribute("prodotto", prodotto);
            return "/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }
}
