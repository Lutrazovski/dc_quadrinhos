package br.senac.resource;

import br.senac.dto.QuadrinhoDto;
import br.senac.service.QuadrinhoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.sql.SQLException;
import java.util.List;

@Path("/quadrinhos")
@Tag(name = "Quadrinhos Resource", description = "Endpoints para gerenciar")

public class QuadrinhoResource {

    @Inject
    QuadrinhoService quadrinhoService;

    @POST
    @Path("/quadrinhos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Criar um novo Quadrinho")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Quadrinho criado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor"),
    })

    public Response cadastrarQuadrinho(QuadrinhoDto quadrinho) {
        try {
            quadrinhoService.createQuadrinho(quadrinho);
            return Response.status(Response.Status.CREATED).entity(quadrinho).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualizar um Quadrinho existente")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Quadrinho atualizado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response atualizarQuadrinho(QuadrinhoDto quadrinho) {
        try {
            quadrinhoService.updateQuadrinho(quadrinho);
            return Response.ok(quadrinho).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletar quadrinho pelo ID")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Quadrinho atualizado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response deletarQuadrinho(@PathParam("id") int id) {
        try {
            quadrinhoService.deleteQuadrinho(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter uma Quadrinho pelo ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Quadrinho obtido com sucesso"),
            @APIResponse(responseCode = "404", description = "Quadrinho não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response obterQuadrinhoPorId(@PathParam("id") int id) {
        try {
            QuadrinhoDto quadrinho = quadrinhoService.getQuadrinhoById(id);
            if (quadrinho != null) {
                return Response.ok(quadrinho).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter todos os Quadrinhos")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Quadrinhos obtidos com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response obterTodosQuadrinhos() {
        try {
            List<QuadrinhoDto> quadrinhos = quadrinhoService.getAllQuadrinhos();
            return Response.ok(quadrinhos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
