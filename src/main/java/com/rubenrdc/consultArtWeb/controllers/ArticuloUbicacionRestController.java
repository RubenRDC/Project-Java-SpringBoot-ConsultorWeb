package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloUbicacionDTO;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
