package com.rubenrdc.consultArtWeb.Dao;

import com.rubenrdc.consultArtWeb.models.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruben
 */
public interface IDepositoDao extends JpaRepository<Deposito, Integer> {
    Deposito findByDescrip(String descrip);
}
