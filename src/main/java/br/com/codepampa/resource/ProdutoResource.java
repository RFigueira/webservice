package br.com.codepampa.resource;

import br.com.codepampa.model.Produto;
import br.com.codepampa.service.ProdutoService;

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


@Path("/api/produto")
public class ProdutoResource {

    private ProdutoService produtoService;

    @PostConstruct
    private void init(){
        produtoService = new ProdutoService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> helloWord() {
        return produtoService.findAll();
    }

    @GET
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto findByPk(@DefaultValue("0") @PathParam("pk") Long pk) {
        return produtoService.findByPk(pk);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto save(Produto produto) {
        return produtoService.save(produto);
    }

    @PUT
    @Path("/{pk}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto editar(Produto produto) {
        return produtoService.save(produto);
    }

    @DELETE
    @Path("/{pk}")
    @Produces(MediaType.APPLICATION_JSON)
    public void excluir(@DefaultValue("0") @PathParam("pk") Long pk) {
        if(pk > 0){
            produtoService.removeByPk(pk);
        }
    }
}
