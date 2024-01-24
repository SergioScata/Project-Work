package org.lesson.projectwork.controller;

import org.lesson.projectwork.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ProdottoController {
    @Autowired
    private ProdottoRepository prodottoRepository;
}
