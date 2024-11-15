package com.rubenrdc.consultArtWeb.models;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ruben
 */
public class ArticuloUbicacionDTO {

    @Nullable
    private int id;
    
    @Range(min = 1,max = 100000)
    private int stockInUbicacion;
    
    @NotBlank
    @Length(max = 11, min = 11)
    private String ubicacion;
    
    @Length(max = 45, min = 5)
    @NotBlank
    private String deposito;

    public ArticuloUbicacionDTO(int id, int stockArt, String ubicacion, String deposito) {
        this.id = id;
        this.stockInUbicacion = stockArt;
        this.ubicacion = ubicacion;
        this.deposito = deposito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockInUbicacion() {
        return stockInUbicacion;
    }

    public void setStockInUbicacion(int stockInUbicacion) {
        this.stockInUbicacion = stockInUbicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }
}
