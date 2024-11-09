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
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping(path = "articulos", params = {"codigo", "descripcion"})
    public ResponseEntity<?> getArticuloLikeDescrip(@RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion, @RequestParam(name = "codigo", required = false, defaultValue = "") String codigo) {
        List<ArticuloDTO> findAllArt = artDao.findArticuloDTOLikeDesc(codigo, descripcion);
        if (!findAllArt.isEmpty()) {
            return new ResponseEntity<>(findAllArt, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping(path = "articulos/{x}")
    public ResponseEntity<?> getArticulo(@PathVariable String x) {
        ArticuloDTO find = null;
        try {
            int v = Integer.parseInt(x);
            find = artDao.findArticuloDTOCompleteById(v);
        } catch (NumberFormatException e) {
            find = artDao.findArticuloDTOCompleteByCode(x);
        }
        /*switch (x) {//Java 17+ intanceof implicito en el switch!!
            case String code ->
                find = artDao.findArticuloDTOCompleteByCode(code);
            case Integer id ->
                find = artDao.findArticuloDTOCompleteById(id);
            default -> {
            }
        }*/
        if (find != null) {
            return new ResponseEntity<>(find, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }
}
