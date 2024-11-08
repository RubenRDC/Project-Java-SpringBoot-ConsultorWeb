package com.rubenrdc.consultArtWeb.models;

/**
 *
 * @author Ruben
 */
public class ArticuloUbicacionDTO {

    private int stockInUbicacion;
    private String ubicacion;
    private String deposito;

    public ArticuloUbicacionDTO(int stockArt, String ubicacion, String deposito) {
        this.stockInUbicacion = stockArt;
        this.ubicacion = ubicacion;
        this.deposito = deposito;
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
