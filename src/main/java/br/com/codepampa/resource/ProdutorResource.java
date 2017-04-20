package br.com.codepampa.resource;

import br.com.codepampa.model.Produtor;
import br.com.codepampa.service.ProdutorService;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class ProdutorResource {

    ProdutorService produtorService;

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

}
