package org.lesson.projectwork.repository;

import org.lesson.projectwork.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
    List<Prodotto> findByNameContaining(String searchName);
}
