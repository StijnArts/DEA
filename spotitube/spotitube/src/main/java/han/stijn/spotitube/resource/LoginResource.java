package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/")
public class LoginResource {

    private LoginDAO loginDAO;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Response login(LoginDTO loginDTO){
        if(loginDAO.checkLogin(loginDTO)){
            return Response.ok(loginDAO.getUser(loginDTO)).build();
        }
        return Response.status(403).build();
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
