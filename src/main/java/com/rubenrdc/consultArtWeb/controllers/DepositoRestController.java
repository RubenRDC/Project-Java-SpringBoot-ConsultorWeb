package com.rubenrdc.consultArtWeb.controllers;

import com.rubenrdc.consultArtWeb.Dao.IDepositoDao;
import com.rubenrdc.consultArtWeb.models.Deposito;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if(!findAll.isEmpty()){
            return ResponseEntity.ok().body(findAll);
        }
        return ResponseEntity.ok().body(List.of());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDepById(@PathVariable int id) {
        Optional<Deposito> findAll = DepDao.findById(id);
        if(findAll.isPresent()){
            return ResponseEntity.ok().body(findAll);
        }
        return ResponseEntity.ok().body(List.of());
    }
    
}
