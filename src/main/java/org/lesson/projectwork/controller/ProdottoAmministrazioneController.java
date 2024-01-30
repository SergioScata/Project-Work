package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Assortimento;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.AssortimentoRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/amministrazione")
public class ProdottoAmministrazioneController {
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private AssortimentoRepository assortimentoRepository;

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

    @GetMapping("/create")
    public String create(Model model) {
        Prodotto prodotto = new Prodotto();
        model.addAttribute("prodotto", prodotto);
        return "amministrazione/create";
    }

    @PostMapping("/create")
    public String create2(@Valid @ModelAttribute("prodotto") Prodotto formProdotto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("prodotto", prodottoRepository.findAll());
            return "amministrazione/create";
        }
        Prodotto savedProdotto = prodottoRepository.save(formProdotto);
        return "redirect:/amministrazione/show/" + savedProdotto.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Prodotto> result = prodottoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("prodotto", result.get());
            return "amministrazione/create";
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
                return "/amministrazione/create";
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
        Prodotto prodottoToDelete = result.get();
        if (result.isPresent()) {
            prodottoRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("redirectMessage", result.get().getNome() + " è stato cancellato!");
            return "redirect:/amministrazione";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id" + id + "not found!");
        }
    }

    @GetMapping("/transazioni")
    public String index(Model model) {
        List<Acquisto> acquistoList;

        acquistoList = acquistoRepository.findAll();

        model.addAttribute("acquistoList", acquistoList);
        return "amministrazione/listaAcquisti";
    }

    @GetMapping("/contabilita")
    public String contabilita(Model model) {
        List<Acquisto> acquisti = acquistoRepository.findAll();
        List<Assortimento> assortimenti = assortimentoRepository.findAll();
        List<Object> entries = new ArrayList<>();
        for (Acquisto acquisto : acquisti) {
            entries.add(acquisto);
        }
        for (Assortimento assortimento : assortimenti) {
            entries.add(assortimento);
        }
        entries.sort((e1, e2) -> {
            if (e1 instanceof Acquisto && e2 instanceof Acquisto) {
                return ((Acquisto) e2).getDataAcquisto().compareTo(((Acquisto) e1).getDataAcquisto());
            } else if (e1 instanceof Assortimento && e2 instanceof Assortimento) {
                return ((Assortimento) e2).getDataAssortimento().compareTo(((Assortimento) e1).getDataAssortimento());
            } else {
                return 0;
            }
        });
        model.addAttribute("entrate", entries);
        return "amministrazione/contabilita";
    }
}
