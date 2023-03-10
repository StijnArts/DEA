package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/")
public class TrackResource {

    private LoginDAO loginDAO;
    private TrackDAO trackDAO;

    @GET
    @Path("/playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("id") int id, @QueryParam("token") String token){
        if(loginDAO.checkToken(token)) {
            return Response.ok(trackDAO.getTracks(id)).build();
        }
        return Response.status(403).build();
    }

    @GET
    @Path("/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAvailableTracks(@QueryParam("forPlaylist")int playlistID,@QueryParam("token") String token){
        if(loginDAO.checkToken(token)) {
            return Response.ok(trackDAO.getAvailableTracks(playlistID)).build();
        }
        return Response.status(403).build();
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setTracksDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
