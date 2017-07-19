package br.com.codepampa.service;

import br.com.codepampa.model.Usuario;
import br.com.codepampa.util.Criptografia;

import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rfreitas on 08/05/17.
 */
public class UsuarioService extends GenericService<Usuario, Long> {

    public boolean checarLogin(Usuario usuario) {
        Query query = entityManager.createNamedQuery("Usuario.login");
        query.setParameter("login", usuario.getLogin());
        query.setParameter("senha", usuario.getSenha());
        return query.getResultList().size() > 0;
    }

    public Usuario login(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Criptografia.generateHash(usuario.getSenha()));
        if(this.checarLogin(usuario)) {
            Query query = entityManager.createNamedQuery("Usuario.findByLogin");
            query.setParameter("login", usuario.getLogin());
            usuario = (Usuario) query.getSingleResult();
            String token = Criptografia.base64encode("login=" + usuario.getLogin() + "&" + "senha=" + usuario.getSenha());
            usuario.setLogin("");
            usuario.setSenha("");
            usuario.setToken(token);
        } else {
            usuario.setToken("");
        }
        return usuario;
    }
}

