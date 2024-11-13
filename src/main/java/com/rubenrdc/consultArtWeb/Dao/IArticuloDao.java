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

    Articulo findArticuloById(int id);

    @Query("SELECT new com.rubenrdc.consultArtWeb.models.ArticuloDTO( a.id, a.codigo, a.descripcion,a.foto ) FROM Articulo a WHERE a.codigo= ?1")
    ArticuloDTO findArticuloDTOByCodigo(String codigo);

    @Query("SELECT new com.rubenrdc.consultArtWeb.models.ArticuloDTO( a.id, a.codigo, a.descripcion,a.foto ) FROM Articulo a WHERE a.id= ?1")
    ArticuloDTO findArticuloDTOById(int id);

    @Query("SELECT new com.rubenrdc.consultArtWeb.models.ArticuloDTO( id, codigo, descripcion,foto ) FROM Articulo")
    List<ArticuloDTO> findAllSimple();

    @Query("SELECT new com.rubenrdc.consultArtWeb.models.ArticuloDTO( ar.id, ar.codigo, ar.descripcion, ar.foto ) FROM Articulo ar WHERE ar.codigo LIKE %?1% AND ar.descripcion LIKE %?2%")
    List<ArticuloDTO> findAllSimpleLikeDescripcion(String codigo, String descripcion);
}
