package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.Dao.IDepositoDao;
import com.rubenrdc.consultArtWeb.models.Deposito;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ruben
 */
@RestController
@RequestMapping(path = "api/depositos")
public class DepositoRestController {

    @Autowired
    private IDepositoDao DepDao;

    @GetMapping()
    public ResponseEntity<?> getDepList() {
        List<Deposito> findAll = DepDao.findAll();
        if (!findAll.isEmpty()) {
            return ResponseEntity.ok().body(findAll);
        }
        return ResponseEntity.ok().body(List.of());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDepById(@PathVariable int id) {
        Optional<Deposito> findAll = DepDao.findById(id);
        if (findAll.isPresent()) {
            return ResponseEntity.ok().body(findAll);
        }
        return ResponseEntity.ok().body(List.of());
    }

    @PostMapping()
    public ResponseEntity<?> createDep(@Valid @RequestBody(required = true) Deposito dep) {
        Deposito save = DepDao.save(dep);
        if (save != null) {
            return new ResponseEntity(save, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateFullDep(@PathVariable(required = true) int id, @RequestBody(required = true) Deposito dep) {
        Optional<Deposito> findById = DepDao.findById(id);
        if (findById.isPresent()) {
            Deposito get = findById.get();
            get.setDescrip(dep.getDescrip());
            get.setProvincia(dep.getProvincia());
            get.setLocalidad(dep.getLocalidad());
            get.setDireccion(dep.getDireccion());
            get.setNumero(dep.getNumero());
            return ResponseEntity.ok(DepDao.save(get));
        }
        return new ResponseEntity(Map.of("error","Elemento que intenta actualizar completamente no se encuentra almacenado."), HttpStatus.NOT_FOUND);
    }

}
