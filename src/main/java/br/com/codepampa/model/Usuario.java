package br.com.codepampa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by rfreitas on 08/05/17.
 */
@Entity
public class Usuario extends BaseEntity {

    @Column(name = "nome", length = 125)
    private String nome;

    @Column(name = "login", length = 125)
    private String login;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "senha")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
