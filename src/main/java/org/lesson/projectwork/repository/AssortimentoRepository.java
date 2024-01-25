package org.lesson.projectwork.repository;

import org.lesson.projectwork.model.Assortimento;
import org.lesson.projectwork.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssortimentoRepository extends JpaRepository<Assortimento, Integer> {
}
