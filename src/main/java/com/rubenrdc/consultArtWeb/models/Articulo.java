package com.rubenrdc.consultArtWeb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "articulos")
public class Articulo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 165)
    private String codigo, descripcion, foto;

    @OneToMany(mappedBy = "articulo", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ArticuloUbicacion> listCantsFromUbics;

    public Articulo(int idArt, String code) {
        this.id = idArt;
        this.codigo = code;
    }

    public Articulo() {
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

    public String getUbicsConcat(String DepName) {
        if (listCantsFromUbics != null) {
            if (!listCantsFromUbics.isEmpty()) {
                String ubicConcat = listCantsFromUbics.stream()
                        .filter(art -> art.getDeposito().getDescrip().equals(DepName))//Filtro el stream y devuelve uno con el filtro aplicado
                        .map(art -> art.getUbicacion().getUbic())//Devuelvo un stream con las ubicaciones filtradas
                        .collect(Collectors.joining(" | "));//Realizo una coleccion con los valores del anterior Stream y genero una concatenacion devolviendo un String
                return ubicConcat;
            }
        }
        return null;
    }

    public List<Ubicacion> getListUbicByDepName(String DepName) {
        if (listCantsFromUbics != null) {
            if (!listCantsFromUbics.isEmpty()) {
                List<Ubicacion> collect = listCantsFromUbics.stream().filter(ubi -> ubi.getDeposito().getDescrip().equals(DepName)).map(art -> art.getUbicacion()).collect(Collectors.toList());
                return collect;
            }
        }
        return null;
    }

    public Map<String, Integer> getStockByDeps() {
        if (listCantsFromUbics != null) {
            if (!listCantsFromUbics.isEmpty()) {
                Map<String, Integer> collect = listCantsFromUbics.stream().collect(Collectors.groupingBy(art -> art.getDeposito().getDescrip(), Collectors.summingInt(ArticuloUbicacion::getStockArt)));
                return collect;
            }
        }
        return null;
    }

    public List<ArticuloUbicacion> getListCantsFromUbics() {
        return listCantsFromUbics;
    }

    public void setListCantsFromUbics(List<ArticuloUbicacion> listCantsFromUbics) {
        this.listCantsFromUbics = listCantsFromUbics;
    }
}
