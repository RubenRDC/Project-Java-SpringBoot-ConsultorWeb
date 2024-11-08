package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Ruben
 */
@Controller
public class ArticuloController {

    @Autowired
    private IArticuloDao artDao;
    @Autowired
    private IUbicacionDao ubicDao;

    @GetMapping(path = "/articulos/{codigo}")
    public String infoArticuloView(Model model, @PathVariable String codigo) {
        if (!codigo.isBlank()) {
            Articulo findArticuloByCodigo = artDao.findArticuloByCodigo(codigo);
            if (findArticuloByCodigo != null) {
                findArticuloByCodigo.setListCantsFromUbics(ubicDao.findUbicacionByArticulo(codigo));
                ArticuloDTO artDTO = new ArticuloDTO(findArticuloByCodigo);
                model.addAttribute("Articulo", artDTO);
            } else {
                return "/";
            }
        }
        return "articulo";
    }

}
