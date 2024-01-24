package org.lesson.projectwork.repository;

import org.lesson.projectwork.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
}
