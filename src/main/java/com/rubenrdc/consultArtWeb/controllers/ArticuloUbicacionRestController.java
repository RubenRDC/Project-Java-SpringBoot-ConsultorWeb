package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.models.ArticuloUbicacionDTO;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ruben
 */
@RestController
@RequestMapping(path = "api/articulos/{codigo}/ubicaciones")
public class ArticuloUbicacionRestController {

    @Autowired
    private ArticuloUbicacionService artUbicService;

    @GetMapping()
    public ResponseEntity<?> getListUbicByArticulo(@PathVariable(required = true, name = "codigo") String codigo) {
        List<ArticuloUbicacionDTO> findArticuloUbicacionbyCodeArticulo = artUbicService.findArticuloUbicacionbyCodeArticulo(codigo);
        if (!findArticuloUbicacionbyCodeArticulo.isEmpty()) {
            return ResponseEntity.ok().body(findArticuloUbicacionbyCodeArticulo);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> AddUbicInArt(@PathVariable(required = true) String codigo, @Valid @RequestBody(required = true) ArticuloUbicacionDTO ubic) {
        Map saveArticuloUbicacion = artUbicService.saveArticuloUbicacion(ubic, codigo);
        if (saveArticuloUbicacion.isEmpty()) {
            return new ResponseEntity(ubic, HttpStatus.CREATED);
        }
        return new ResponseEntity(saveArticuloUbicacion, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> DelectUbicInArt(@PathVariable(required = true) String codigo, @PathVariable(required = true) int id) {
        if (artUbicService.deleteArticuloUbicacionByIdAndCode(id, codigo)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
