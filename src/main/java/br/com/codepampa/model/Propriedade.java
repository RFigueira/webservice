package br.com.codepampa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Propriedade extends BaseEntity {

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "latitude", length = 80)
    private String latitude;

    @Column(name = "longitude", length = 80)
    private String longitude;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String getLongitude() {
        return longitude;
    }

    private void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Propriedade)) {
            return false;
        }
        Propriedade that = (Propriedade) o;
        return Objects.equals(this.latitude, that.getLatitude()) &&
                Objects.equals(this.longitude, that.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
