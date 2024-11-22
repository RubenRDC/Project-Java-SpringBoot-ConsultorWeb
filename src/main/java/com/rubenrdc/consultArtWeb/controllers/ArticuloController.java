package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping()
    public String getlistArticulos(Model model) {
        model.addAttribute("articulos", artDao.findAllArticulosDTO());
        return "listaArticulos";
    }

    @GetMapping(params = {"codigo", "descripcion"})
    public String getArticulosLikeDescrip(Model model, @RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion, @RequestParam(name = "codigo", required = false, defaultValue = "") String codigo) {
        List<ArticuloDTO> findAllArt = artDao.findArticuloDTOLikeDesc(codigo, descripcion);
        model.addAttribute("articulos", findAllArt);
        if (!findAllArt.isEmpty()) {
            return "listaArticulos";
        }
        return "listaArticulos";
    }
}
