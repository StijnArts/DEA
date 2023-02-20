package nl.han.oose.dea.resources;

import jakarta.ws.rs.*;

@Path("/health")
public class HealthCheckResource {

    @GET
    public String healthy(){
        return "Up & Running";
    }
}
