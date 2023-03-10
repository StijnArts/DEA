package nl.han.ica.oose.dea.resources;

import jakarta.inject.*;
import nl.han.ica.oose.dea.services.*;
import nl.han.ica.oose.dea.services.dto.ItemDTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/items")
public class ItemResource {

    @Inject
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    private ItemService itemService;

    public ItemResource() {

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTextItems() {
        return "bread, butter";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonItems() {
        return Response.ok().entity(itemService.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemDTO itemDTO) {
        itemService.addItem(itemDTO);

        return Response.created(
            UriBuilder.fromPath("items/{id}").build(itemDTO.getId())
        ).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        return Response.ok().entity(itemService.getItem(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") int id) {
        itemService.deleteItem(id);
        return Response.ok().build();
    }
}
