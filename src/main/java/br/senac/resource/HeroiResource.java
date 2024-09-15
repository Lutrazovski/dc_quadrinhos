package br.senac.resource;

import br.senac.dto.HeroiDto;
import br.senac.service.HeroiService;
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

@Path("/herois")
@Tag(name = "Herois Resource", description = "Endpoints para gerenciar")

public class HeroiResource {

    @Inject
    HeroiService heroiService;

    @POST
    @Path("/herois")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Criar um novo Heroi")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Heroi criado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor"),
    })
    public Response cadastrarHeroi(HeroiDto heroi) {
        try {
            heroiService.createQuadrinho(heroi);
            return Response.status(Response.Status.CREATED).entity(heroi).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualizar um Heroi existente")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Quadrinho atualizado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response atualizarHeroi(HeroiDto heroi) {
        try {
            heroiService.updateQuadrinho(heroi);
            return Response.ok(heroi).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deletar Heroi pelo ID")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Heroi atualizado com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response deletarHeroi(@PathParam("id") int id) {
        try {
            heroiService.deleteHeroi(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter um Heroi pelo ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Heroi obtido com sucesso"),
            @APIResponse(responseCode = "404", description = "Heroi não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response obterQuadrinhoPorId(@PathParam("id") int id) {
        try {
            HeroiDto heroi = heroiService.getHeroiById(id);
            if (heroi != null) {
                return Response.ok(heroi).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter todos os Herois")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Herois obtidos com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response obterTodosHerois() {
        try {
            List<HeroiDto> herois = heroiService.getAllHerois();
            return Response.ok(herois).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
