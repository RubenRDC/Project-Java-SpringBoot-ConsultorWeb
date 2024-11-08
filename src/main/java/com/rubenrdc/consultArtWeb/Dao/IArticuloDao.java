package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ruben
 */
public interface IArticuloDao extends JpaRepository<Articulo, Integer> {

    Articulo findArticuloByCodigo(String codigo);

    @Query("SELECT new com.rubenrdc.consultArtWeb.models.ArticuloDTO( id, codigo, descripcion,foto ) FROM Articulo")
    List<ArticuloDTO> findAllSimple();
}
