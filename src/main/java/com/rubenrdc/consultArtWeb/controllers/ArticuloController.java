package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
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
public class ArticuloController {

    @Autowired
    private IArticuloDao artDao;
    @Autowired
    private IUbicacionDao ubicDao;

    @GetMapping(path = "articulo/{codigo}")
    public ResponseEntity<?> getArticulo(@PathVariable String codigo) {
        Articulo findArticuloByCodigo = artDao.findArticuloByCodigo(codigo);
        if (findArticuloByCodigo != null) {
            findArticuloByCodigo.setListCantsFromUbics(ubicDao.findUbicacionByArticulo(codigo));
        }
        return new ResponseEntity<>(findArticuloByCodigo, HttpStatus.OK);
    }

    @GetMapping("/test/{codigo}")
    public ResponseEntity<?> getUbic(@PathVariable String codigo) {

        return new ResponseEntity<>(ubicDao.findUbicacionByArticulo(codigo), HttpStatus.OK);
    }

}
