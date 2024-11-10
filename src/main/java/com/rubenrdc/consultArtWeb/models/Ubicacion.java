package com.rubenrdc.consultArtWeb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "ubicaciones")
public class Ubicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 65, unique = true)
    @Length(max = 20)
    @NotBlank
    private String ubic;

    public Ubicacion() {
    }

    public Ubicacion(int id, String concatUbic) {//Para Update
        this.id = id;
        this.ubic = concatUbic;
    }

    public Ubicacion(String concatUbic) {//Para Update
        this.ubic = concatUbic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbic() {
        return ubic;
    }

    public void setConcatUbic(char sector, int pasillo, int estante, int cajon, int altura) {
        String p = Integer.toString(pasillo), e = Integer.toString(estante), c = Integer.toString(cajon), a = Integer.toString(altura);
        if (pasillo < 10) {
            p = "0" + pasillo;
        }
        if (estante < 10) {
            e = "0" + estante;
        }
        if (cajon < 10) {
            c = "0" + cajon;
        }
        if (altura < 10) {
            a = "0" + altura;
        }

        this.ubic = sector + p + "-" + e + c + "-" + a;
    }

    public void setUbic(String ConcatUbic) {
        this.ubic = ConcatUbic;
    }

}
