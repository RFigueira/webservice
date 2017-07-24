package br.com.codepampa.resource;

import br.com.codepampa.model.Usuario;
import br.com.codepampa.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rfreitas on 18/07/17.
 */
@Path("/api/login")
public class LoginResource {

    private UsuarioService usuarioService;

    @PostConstruct
    private void init() {
        usuarioService = new UsuarioService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws NoSuchAlgorithmException {
        usuario = usuarioService.login(usuario);
        if (!usuario.getToken().equals("")) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
