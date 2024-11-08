package com.rubenrdc.consultArtWeb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ruben
 */
public class ArticuloDTO {

    private int id;
    private String codigo, descripcion, foto;
    private List<ArticuloUbicacionDTO> listCantsFromUbics;
    private Map<String, Integer> stockTotalByDeps;

    public ArticuloDTO(Articulo x) {
        this.id = x.getId();
        this.codigo = x.getCodigo();
        this.descripcion = x.getDescripcion();
        this.foto = x.getFoto();
        this.stockTotalByDeps = x.getStockByDeps();

        listCantsFromUbics = new ArrayList<>();
        x.getListCantsFromUbics().stream().forEach(e -> {
            listCantsFromUbics.add(new ArticuloUbicacionDTO(e.getStockArt(), e.getUbicacion().getUbic(), e.getDeposito().getDescrip()));
        });
    }

    public ArticuloDTO(int id, String codigo, String descripcion, String foto) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<ArticuloUbicacionDTO> getListCantsFromUbics() {
        return listCantsFromUbics;
    }

    public void setListCantsFromUbics(List<ArticuloUbicacionDTO> x) {
        this.listCantsFromUbics = x;
    }

    public Map<String, Integer> getStockTotalByDeps() {
        return stockTotalByDeps;
    }

    public void setStockTotalByDeps(Map<String, Integer> stockTotalByDeps) {
        this.stockTotalByDeps = stockTotalByDeps;
    }
}
