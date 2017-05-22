package br.com.codepampa.resource;

import br.com.codepampa.model.Usuario;
import br.com.codepampa.service.UsuarioService;
import br.com.codepampa.util.Constantes;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by rfreitas on 08/05/17.
 */
@Path("api/usuario")
public class UsuarioResource {

    private UsuarioService usuarioService;

    @PostConstruct
    private void init() {
        usuarioService = new UsuarioService();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario save(Usuario usuario) {
        if(upload(usuario)) {
            return usuarioService.save(usuario);
        }
        return null;
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
