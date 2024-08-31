package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruben
 */
public interface IArticuloDao extends JpaRepository<Articulo, Integer>{
    Articulo findArticuloByCodigo(String codigo);
}
