package nl.han.oose.dea.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import nl.han.oose.dea.*;
import nl.han.oose.dea.dto.*;
import nl.han.oose.dea.exceptions.*;

import java.awt.*;
import java.util.List;

@Path("/items")
public class GetItems {

    private ItemService itemService;

    public GetItems(){
        itemService = new ItemService();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public Response getItems(){
        try{
            return Response.status(200).entity(itemService.getAll()).build();
        } catch (ItemNotAvailableException e){
            return Response.status(404).build();
        }

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getItem(@PathParam("id") int id){
        try{
            return Response.status(200).entity(itemService.getItem(id)).build();
        } catch (ItemNotAvailableException e){
            return Response.status(404).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addItem(ItemDTO itemDTO){
        try{
            itemService.addItem(itemDTO);
            return Response.status(200).build();
        } catch (IdAlreadyInUseException e){
            return Response.status(409).build();
        }

    }

}
