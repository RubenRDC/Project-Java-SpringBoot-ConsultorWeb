package com.rubenrdc.consultArtWeb.models;

import com.rubenrdc.consultorArtWeb.models.interfaces.Exportable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "depositos")
public class Deposito implements Exportable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 11)
    private int numero;
    @Column(length = 100)
    private String descrip, provincia, localidad, direccion;
    
    @Transient
    private final Object[] row = new Object[3];

    public Deposito() {
    }

    @Override
    public Object[] getRow() {
        row[0] = id;
        row[1] = descrip;
        row[2] = provincia;
        return row;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
