package br.com.codepampa.util;

import br.com.codepampa.model.Usuario;
import br.com.codepampa.service.UsuarioService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Created by rfreitas on 18/07/17.
 */
public class Token {

   private Token(){}

   private UsuarioService usuarioService;

   public Token(UsuarioService usuarioService) {
       this.usuarioService = usuarioService;
   }

   private boolean checarToken(String token) {
        try {
            return usuarioService.checarLogin(getUsuario(token));
        } catch (Exception ex) {
            return false;
        }
    }

    private Usuario getUsuario(String token) {
        String dadosLogin;
        dadosLogin = Criptografia.base64decode(token);
        String dadosSplit[] = dadosLogin.split("&");
        String login = dadosSplit[0].replace("login=", "");
        String senha = dadosSplit[1].replace("senha=", "");

        return getInstancia(login, senha);
    }

    private Usuario getInstancia(String login, String senha) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        return usuario;
    }

    public boolean filtro(@Context HttpHeaders headers) {
        String token = headers.getRequestHeader("Authorization").get(0);
        return checarToken(token);
    }

}
