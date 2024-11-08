package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
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
@RequestMapping(path = "api/")
public class ArticuloRestController {

    @Autowired
    private ArticuloUbicacionService artDao;

    @GetMapping(path = "articulos")
    public ResponseEntity<?> getAllArticulo() {
        List<ArticuloDTO> findAllArt = artDao.findAllArticulosDTO();
        if (!findAllArt.isEmpty()) {
            return new ResponseEntity<>(findAllArt, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    
    @GetMapping(path = "articulos", params = {"descripcion"})
    public ResponseEntity<?> getArticuloLikeDescrip(@RequestParam(name = "descripcion", required = true) String descripcion) {
        List<ArticuloDTO> findAllArt = artDao.findArticuloDTOLikeDesc(descripcion);
        if (!findAllArt.isEmpty()) {
            return new ResponseEntity<>(findAllArt, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "articulos/{codigo}")
    public ResponseEntity<?> getArticulo(@PathVariable String codigo) {
        ArticuloDTO find = artDao.findArticuloDTOCompleteByCode(codigo);
        if (find != null) {
            return new ResponseEntity<>(find, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
