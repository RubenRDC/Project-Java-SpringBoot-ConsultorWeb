package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import com.rubenrdc.consultArtWeb.models.Deposito;
import com.rubenrdc.consultArtWeb.services.ArticuloUbicacionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @PostMapping()
    public ResponseEntity<?> createArticulo(@Valid @RequestBody(required = true) Articulo art) {
        Articulo saveArticulo = artDao.saveArticulo(art);
        if (saveArticulo != null) {
            return new ResponseEntity(saveArticulo, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateFullArticulo(@PathVariable(required = true) int id, @Valid @RequestBody(required = true) Articulo art) {
        Articulo findById = artDao.findById(id);
        if (findById != null) {
            findById.setCodigo(art.getCodigo());
            findById.setDescripcion(art.getDescripcion());
            findById.setFoto(art.getFoto());
            return ResponseEntity.ok(artDao.saveArticulo(findById));
        }
        return new ResponseEntity(Map.of("error", "Elemento que intenta actualizar completamente no se encuentra almacenado."), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticulo(@PathVariable(required = true) int id) {
        if (artDao.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity(Map.of("error", "Elemento que intenta eliminar completamente no se encuentra."), HttpStatus.NOT_FOUND);
    }
}
