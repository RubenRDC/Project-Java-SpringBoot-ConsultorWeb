package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.ArticuloUbicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ruben
 */
public interface IUbicacionDao extends JpaRepository<ArticuloUbicacion, Integer>{
    @Query("""
           SELECT ua
           FROM ArticuloUbicacion ua
           JOIN FETCH ua.articulo art
           LEFT JOIN FETCH ua.deposito d
           LEFT JOIN FETCH ua.ubicacion ux
           WHERE art.codigo = ?1
           ORDER BY ua.deposito
           """)
    List<ArticuloUbicacion> findUbicacionByArticulo(String codigo);
}
