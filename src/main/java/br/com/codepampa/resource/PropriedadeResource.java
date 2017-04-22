package br.com.codepampa.resource;

import br.com.codepampa.model.Propriedade;
import br.com.codepampa.service.PropriedadeService;

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

//Nesse arquivo, foi utilizado o caminho do recurso [URI] no path da classe
//Os metodos que nao tem @Path anotados, ele coloca por Default o caminho da classe
//Chamando o metodo referente aquele tipo de request
// - Guris, se nao entenderem me chamem eu explico na aula :)

@Path("/api/propriedade")
public class PropriedadeResource {

   private PropriedadeService propriedadeService;

    @PostConstruct
    public void init(){
        propriedadeService = new PropriedadeService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Propriedade> helloWord() {
        return propriedadeService.findAll();
    }

    @GET
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade findByPk(@DefaultValue("0") @PathParam("pk") Long pk) {
        return propriedadeService.findByPk(pk);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade save(Propriedade propriedade) {
       return propriedadeService.save(propriedade);
    }

    @PUT
    @Path("/{pk}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade editar(Propriedade propriedade) {
        return propriedadeService.save(propriedade);
    }

    @DELETE
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public void excluir(@DefaultValue("0") @PathParam("pk") Long pk) {
      if(pk > 0){
          propriedadeService.removeByPk(pk);
      }
    }

}
