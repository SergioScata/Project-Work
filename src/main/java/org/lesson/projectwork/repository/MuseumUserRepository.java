package org.lesson.projectwork.repository;

import org.lesson.projectwork.model.MuseumUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MuseumUserRepository extends JpaRepository<MuseumUser, Integer> {
    Optional<MuseumUser> findByEmail(String email);
}
