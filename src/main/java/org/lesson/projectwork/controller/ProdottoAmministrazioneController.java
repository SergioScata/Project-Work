package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/create")
    public String create(Model model) {
        Prodotto prodotto = new Prodotto();
        model.addAttribute("prodotto", prodotto);
        return "shop/amministrazione/create";
    }

    @PostMapping("/create")
    public String create2(@Valid @ModelAttribute("prodotto") Prodotto formProdotto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("prodotto", prodottoRepository.findAll());
            return "recipe/create";
        }
        Prodotto savedProdotto = prodottoRepository.save(formProdotto);
        return "redirect:/shop/amministrazione/show/" + savedProdotto.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("prodotto", result.get());
            return "amministrazione/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "prodotto with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("prodotto") Prodotto formProdotto, BindingResult bindingResult) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            Prodotto prodottoToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "/amministrazione/edit";
            }
            formProdotto.setFoto(prodottoToEdit.getFoto());
            Prodotto savedProdotto= prodottoRepository.save(formProdotto);

            return "redirect:/amministrazione/show/{id}";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()){
            prodottoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", result.get().getNome() + " è stato cancellato!");
            return "redirect:/amministrazione";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id" + id + "not found!");
        }
    }
}
