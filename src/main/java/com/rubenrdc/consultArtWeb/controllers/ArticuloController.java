package com.rubenrdc.consultArtWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ruben
 */
@Controller
@RequestMapping(path = "/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloUbicacionService artDao;

    @GetMapping(path = "/{codigo}")
    public String infoArticuloView(Model model, @PathVariable(required = true) String codigo) {
        if (!codigo.isBlank()) {
            model.addAttribute("Articulo", artDao.findArticuloDTOCompleteByIdOrCode(codigo));
        }
        return "articulo";
    }

}
