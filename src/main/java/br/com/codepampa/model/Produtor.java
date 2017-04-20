package br.com.codepampa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produtor extends BaseEntity {


    @Column(name = "nome", length = 125)
    private String nome;

    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    //Nesse caso aqui pode ser EAGER, pois um produtor nao vai ter muitaaaaas propiedades
    //a ponto da performace cair
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Propriedade> propriedades = new HashSet<>();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Set<Propriedade> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(Set<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produtor)) {
            return false;
        }

        Produtor produtor = (Produtor) o;
        return Objects.equals(this.inscricaoEstadual, produtor.getInscricaoEstadual());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inscricaoEstadual);
    }
}
