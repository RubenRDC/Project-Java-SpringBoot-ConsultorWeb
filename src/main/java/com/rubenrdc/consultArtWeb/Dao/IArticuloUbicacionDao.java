package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.ArticuloUbicacion;
import com.rubenrdc.consultArtWeb.models.ArticuloUbicacionDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ruben
 */
public interface IArticuloUbicacionDao extends JpaRepository<ArticuloUbicacion, Integer> {

    @Query("""
           SELECT ua
           FROM ArticuloUbicacion ua
           JOIN ua.articulo art
           LEFT JOIN FETCH ua.deposito d
           LEFT JOIN FETCH ua.ubicacion ux
           WHERE art.codigo = ?1
           ORDER BY ua.deposito
           """)
    List<ArticuloUbicacion> findUbicacionByCodeArticulo(String codigo);

    @Query("""
           SELECT new com.rubenrdc.consultArtWeb.models.ArticuloUbicacionDTO(
               ua.id, ua.stockArt, ua.ubicacion.ubic, ua.deposito.descrip
           )
           FROM ArticuloUbicacion ua
           JOIN ua.articulo art
           WHERE art.codigo = ?1
           ORDER BY ua.deposito.descrip
       """)
    List<ArticuloUbicacionDTO> findUbicacionDTOByCodeArticulo(String codigo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ubicaciones_articulos(idArt,idUbic,idDep,stockArt) VALUES(:idArt,:idUbic,:idDep,:stockArt) ", nativeQuery = true)
    int addUbicacionInArticulo(@Param(value = "idArt") int idArt, @Param(value = "idUbic") int idUbic, @Param(value = "idDep") int idDep, @Param(value = "stockArt") int stockArt);

    //Este metodo Spring podria inferir la operacion por el nombre pero al dejar dicha operacion a Spring esta crea el objeto completo de ArticuloUbicacion para luego recien eliminarlo...
    @Modifying
    @Transactional
    @Query("DELETE FROM ArticuloUbicacion au WHERE au.id= :id AND au.articulo.codigo = :codigo")
    int deleteByIdAndArticulo_Codigo(int id, String codigo);
}
