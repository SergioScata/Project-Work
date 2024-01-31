package org.lesson.projectwork.repository;

import org.lesson.projectwork.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

}
