package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ruben
 */
@RestController
@RequestMapping(path = "api/articulos")
public class ArticuloRestController {

    @Autowired
    private ArticuloUbicacionService artDao;

    @GetMapping
    public ResponseEntity<?> getAllArticulo() {
        List<ArticuloDTO> findAllArt = artDao.findAllArticulosDTO();
        if (!findAllArt.isEmpty()) {
            return new ResponseEntity<>(findAllArt, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping(params = {"codigo", "descripcion"})
    public ResponseEntity<?> getArticuloLikeDescrip(@RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion, @RequestParam(name = "codigo", required = false, defaultValue = "") String codigo) {
        List<ArticuloDTO> findAllArt = artDao.findArticuloDTOLikeDesc(codigo, descripcion);
        if (!findAllArt.isEmpty()) {
            return new ResponseEntity<>(findAllArt, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping(path = "/{x}")
    public ResponseEntity<?> getArticulo(@PathVariable String x) {
        ArticuloDTO find = artDao.findArticuloDTOCompleteByIdOrCode(x);

        if (find != null) {
            return new ResponseEntity<>(find, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }
}
