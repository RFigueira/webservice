package br.com.codepampa.resource;

import br.com.codepampa.model.Produtor;
import br.com.codepampa.service.ProdutorService;
import br.com.codepampa.service.UsuarioService;
import br.com.codepampa.util.Token;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/produtor")
public class ProdutorResource {

    private ProdutorService produtorService;
    private Token token;

    @PostConstruct
    private void init() {
        produtorService = new ProdutorService();
        token = new Token(new UsuarioService());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context HttpHeaders headers) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(produtorService.findAll()).build();
    }

    @GET
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPk(@Context HttpHeaders headers, @DefaultValue("0") @PathParam("pk") Long pk) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(produtorService.findByPk(pk)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpHeaders headers, Produtor produtor) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(produtorService.save(produtor)).build();
    }

    @PUT
    @Path("/{pk}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(@Context HttpHeaders headers, Produtor produtor) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(produtorService.save(produtor)).build();
    }

    @DELETE
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@Context HttpHeaders headers, @DefaultValue("0") @PathParam("pk") Long pk) {
        if (token.filtro(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (pk > 0) {
            produtorService.removeByPk(pk);
        }
        return Response.ok().build();
    }

}
