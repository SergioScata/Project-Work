package org.lesson.projectwork.controller;

import org.lesson.projectwork.model.Acquisto;
import org.lesson.projectwork.model.Prenotazione;
import org.lesson.projectwork.model.Prodotto;
import org.lesson.projectwork.repository.AcquistoRepository;
import org.lesson.projectwork.repository.PrenotazioneRepository;
import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class PrenotazioneController {
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    ProdottoRepository prodottoRepository;
    @Autowired
    AcquistoRepository acquistoRepository;
    @PostMapping("/conferma/{id}")
    public String Confirm(@PathVariable Integer id, Model model) {
        Optional<Prenotazione> result = prenotazioneRepository.findById(id);

        if (result.isPresent()) {
            Acquisto acquisto = new Acquisto();
            Prenotazione prenotazione = result.get();
            acquisto.setDataAcquisto(prenotazione.getDataAcquisto());
            acquisto.setQuantita(prenotazione.getQuantita());
            acquisto.setProdotto(prenotazione.getProdotto());
            acquisto.setPrezzoSingolo(prenotazione.getPrezzoSingolo());
            acquisto.setCodice(prenotazione.getCodice());
            acquisto.setNome(prenotazione.getNome());
            acquisto.setUser(prenotazione.getUser());
            acquistoRepository.save(acquisto);
            prenotazioneRepository.delete(prenotazione);



            return "redirect:/amministrazione/prenotazioni";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto with id " + id + " not found");
        }
    }
}
