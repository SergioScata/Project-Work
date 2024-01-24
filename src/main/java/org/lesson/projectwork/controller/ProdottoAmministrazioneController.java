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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop/amministrazione")
public class ProdottoAmministrazioneController {
    @Autowired
    private ProdottoRepository prodottoRepository;
    @GetMapping
    public String index(@RequestParam(name ="keyword", required = false) String searchKeyword, Model model) {
        List<Prodotto> prodottoList;
        if (searchKeyword != null ){
            prodottoList= prodottoRepository.findByNameContaining(searchKeyword);
        }else {
            prodottoList = prodottoRepository.findAll();
        }
        model.addAttribute("prodottoList", prodottoList);
        model.addAttribute("preloadSearch", searchKeyword);
        return "amministrazione/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            Prodotto prodotto = result.get();
            model.addAttribute("prodotto", prodotto);
            return "amministrazione/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("prodotto", result.get());

            return "templates/amministrazione/edit";

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
                return "/templates/amministrazione/edit";
            }
            formProdotto.setFoto(prodottoToEdit.getFoto());
            Prodotto savedProdotto = prodottoRepository.save(formProdotto);

            return "redirect:/amministrazione/show/{id}";

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            prodottoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", result.get().getNome() + " è stato cancellato!");
            return "redirect:/amministrazione";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id" + id + "not found!");
        }
    }
}
