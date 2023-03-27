package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/")
public class PlaylistResource {

    private LoginDAO loginDAO;

    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    private TrackResource trackResource;

    @GET
    @Path("/playlists")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getPlaylists(@QueryParam("token") String token){
        if(loginDAO.checkToken(token)) {
            return Response.status(200).entity(playlistDAO.getPlaylists(token)).build();
        }
        return Response.status(403).build();
    }

    @DELETE
    @Path("/playlists/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token){
        if(loginDAO.checkToken(token)) {
            playlistDAO.deletePlaylist(id, token);
            return getPlaylists(token);
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistID, @QueryParam("token") String token){
        if(loginDAO.checkToken(token)){
            if(trackDAO.addTrackToPlaylist(playlistID,trackDTO.getId())){
                return Response.ok(trackResource.getTracks(playlistID,token)).build();
            }
        }
        return Response.status(403).build();
    }

    @DELETE
    @Path("/playlists/{playlistID}/tracks/{trackID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistID") int playlistID,@PathParam("trackID") int trackID, @QueryParam("token") String token){
        if(loginDAO.checkToken(token)){
            if(trackDAO.deleteTrackFromPlaylist(playlistID,trackID)){
                return Response.ok(trackResource.getTracks(playlistID,token)).build();
            }
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(NewPlaylistDTO newPlaylistDTO, @QueryParam("token")String token){
        if(loginDAO.checkToken(token)){
            playlistDAO.addPlaylist(newPlaylistDTO, token);
            return getPlaylists(token);
        }
        return Response.status(403).build();
    }

    @PUT
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPlaylist(NewPlaylistDTO newPlaylistDTO,@PathParam("id") int id, @QueryParam("token")String token){
        if(loginDAO.checkToken(token)){
            playlistDAO.editPlaylist(newPlaylistDTO, token, id);
            return getPlaylists(token);
        }
        return Response.status(403).build();
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public void setTrackResource(TrackResource trackResource) {
        this.trackResource = trackResource;
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}
