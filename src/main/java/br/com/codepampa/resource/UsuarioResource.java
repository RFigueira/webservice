package br.com.codepampa.resource;

import br.com.codepampa.model.Usuario;
import br.com.codepampa.service.UsuarioService;
import br.com.codepampa.util.Constantes;
import br.com.codepampa.util.Criptografia;
import br.com.codepampa.util.Token;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rfreitas on 08/05/17.
 */
@Path("api/usuario")
public class UsuarioResource{

    private UsuarioService usuarioService;
    private Token token;

    @PostConstruct
    private void init() {
        usuarioService = new UsuarioService();
        token = new Token(usuarioService);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario save(Usuario usuario) throws NoSuchAlgorithmException {
        if(upload(usuario)) {
            usuario.setSenha(Criptografia.generateHash(usuario.getSenha()));
            return usuarioService.save(usuario);
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context HttpHeaders headers) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(usuarioService.findAll()).build();
    }

    private boolean upload(Usuario usuario) {
        try {
            String base64Image = usuario.getAvatar().split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            usuario.setAvatar(System.currentTimeMillis() + Constantes.SUFIXO_JPG);
            File file = new File(Constantes.DIRETORIO_RAIZ + usuario.getAvatar());
            ImageIO.write(bufferedImage, Constantes.FORMAT_NAME_JPG, file);
            return  true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }


}
