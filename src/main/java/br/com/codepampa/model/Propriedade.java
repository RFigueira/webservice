package br.com.codepampa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Propriedade extends BaseEntity {

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "latitude", length = 80)
    private String latitude;

    @Column(name = "longitude", length = 80)
    private String longitude;


    @ManyToMany
    @JoinTable(name = "propriedade_produto", joinColumns =
            {@JoinColumn(name = "propriedade_fk")}, inverseJoinColumns =
            {@JoinColumn(name = "produto_fk")})
    private Set<Produto> produtos = new HashSet<>();


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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
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
                Objects.equals(this.longitude, that.getLongitude()) &&
                Objects.equals(this.produtos, that.getProdutos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, produtos);
    }
}
