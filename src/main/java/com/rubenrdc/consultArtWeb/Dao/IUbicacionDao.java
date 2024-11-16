package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruben
 */
public interface IUbicacionDao extends JpaRepository<Ubicacion, Integer> {
    Ubicacion findByUbic(String ubic);
}
