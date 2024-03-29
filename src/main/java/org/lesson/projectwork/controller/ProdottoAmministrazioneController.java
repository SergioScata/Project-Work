package org.lesson.projectwork.controller;

import jakarta.validation.Valid;
import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Assortimento;
import org.lesson.projectwork.model.Prenotazione;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.AssortimentoRepository;
import org.lesson.projectwork.repository.PrenotazioneRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/amministrazione")
public class ProdottoAmministrazioneController {
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private AssortimentoRepository assortimentoRepository;
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
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
        Collections.sort(acquistoList, Comparator.comparing(Acquisto::getDataAcquisto));

        model.addAttribute("acquistoList", acquistoList);
        return "amministrazione/listaAcquisti";
    }
    @GetMapping("/prenotazioni")
    public String prenotazioni(Model model) {
        List<Prenotazione> prenotazioneList;

        prenotazioneList = prenotazioneRepository.findAll();
        Collections.sort(prenotazioneList, Comparator.comparing(Prenotazione::getDataAcquisto));

        model.addAttribute("prenotazioniList", prenotazioneList);
        return "amministrazione/listaPrenotazioni";
    }

    @GetMapping("/contabilita")
    public String contabilita(Model model) {
        List<Acquisto> acquisti = acquistoRepository.findAll();
        List<Assortimento> assortimenti = assortimentoRepository.findAll();
        BigDecimal totalAcquisti = BigDecimal.ZERO;
        BigDecimal totalAssortimenti= BigDecimal.ZERO;
        for (Acquisto a: acquisti){
            totalAcquisti= totalAcquisti.add(a.getPrezzoTotale());
        }
        for (Assortimento a: assortimenti){
            totalAssortimenti= totalAssortimenti.add(a.getPrezzoTotale());
        }
        Collections.sort(acquisti, Comparator.comparing(Acquisto::getDataAcquisto));
        Collections.sort(assortimenti, Comparator.comparing(Assortimento::getDataAssortimento));
        BigDecimal incasso= totalAcquisti.subtract(totalAssortimenti);
        model.addAttribute("acquisti", acquisti);
        model.addAttribute("assortimenti", assortimenti);
        model.addAttribute("totaleCosti",incasso);
        model.addAttribute("totaleEntrate", totalAcquisti);
        model.addAttribute("totaleUscite",totalAssortimenti);
        return "amministrazione/contabilita";
    }
}
