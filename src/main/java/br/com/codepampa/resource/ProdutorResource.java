package br.com.codepampa.resource;

import br.com.codepampa.model.Produtor;
import br.com.codepampa.service.ProdutorService;

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
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class ProdutorResource {

    private ProdutorService produtorService;

    @PostConstruct
    public void init(){
        produtorService = new ProdutorService();
    }

    @GET
    @Path("/produtor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produtor> helloWord() {
        return produtorService.findAll();
    }

    @GET
    @Path("/produtor/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor findByPk(@DefaultValue("0") @PathParam("pk") Long pk) {
        return produtorService.findByPk(pk);
    }

    @POST
    @Path("/produtor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor save(Produtor produtor) {
       return produtorService.save(produtor);
    }

    @PUT
    @Path("/produtor/{pk}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor editar(Produtor produtor) {
        return produtorService.save(produtor);
    }

    @DELETE
    @Path("/produtor/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public void excluir(@DefaultValue("0") @PathParam("pk") Long pk) {
      if(pk > 0){
        produtorService.removeByPk(pk);
      }
    }

}
