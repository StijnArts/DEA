package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import han.stijn.spotitube.exception.*;
import jakarta.inject.*;

import java.sql.*;
import java.util.*;

public class PlaylistDAO {

    private TrackDAO trackDAO;
    private ResultSetReader resultSetReader;
    private PreparedStatementHelper preparedStatementHelper;

    public ArrayList<PlaylistDTO> getPlaylists(String token){
        ArrayList<PlaylistDTO> playlistDTOs = new ArrayList<>();
        try {
            String query = "SELECT pl.PLAYLISTID as [PLAYLISTID], pl.[NAME] as [NAME], pl.[OWNER] as [OWNER] " +
                    "FROM [PLAYLIST] pl " +
                    "WHERE pl.[OWNER] IN (SELECT [USERNAME] FROM [USER] WHERE [TOKEN] = ?) " +
                    "UNION " +
                    "SELECT ps.PLAYLISTID as [PLAYLISTID], p.[NAME] as [NAME],p.[OWNER] as [OWNER] " +
                    "FROM PLAYLIST_SAVED_BY ps " +
                    "JOIN PLAYLIST p on p.PLAYLISTID = ps.PlaylistID " +
                    "where ps.USERNAME in (SELECT [USERNAME] FROM [USER] WHERE [TOKEN] = ?) ";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            parameters.add(SQLParameterFactory.createParameter(2,token));
            ResultSet playlistResults = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
            while(resultSetReader.determineNextInResult(playlistResults)){
                playlistDTOs.add(new PlaylistDTO(
                        resultSetReader.readInteger(playlistResults,"PLAYLISTID"),
                        resultSetReader.readString(playlistResults,"NAME"),
                        checkOwnership(token, resultSetReader.readString(playlistResults,"OWNER")),
                        trackDAO.getTracks(resultSetReader.readInteger(playlistResults,"PLAYLISTID"))
                        )
                );
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found in database.",e);
        }
        return playlistDTOs;
    }

    public void deletePlaylist(int id, String token) {
        if(checkOwnership(id, token)){
            String query = "DELETE FROM PLAYLIST WHERE PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,id));
            preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        }
    }

    @Inject
    public void setTracksDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public boolean checkOwnership(String token, String owner){
        try {
            String query = "SELECT [USERNAME] FROM [USER] WHERE Token = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            ResultSet ownershipResult = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
            while(resultSetReader.determineNextInResult(ownershipResult)){
                String foundUser = resultSetReader.readString(ownershipResult,"USERNAME");
                if(foundUser.equals(owner)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found in database.",e);
        }
        return false;
    }

    public boolean checkOwnership(int id, String token){
        ResultSet ownershipResult;
        try {
            String query = "SELECT [OWNER] FROM PLAYLIST " +
                    "WHERE [OWNER] IN (SELECT [USERNAME] FROM [USER] WHERE Token = ?) AND PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            parameters.add(SQLParameterFactory.createParameter(2, id));
            ownershipResult = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
            return resultSetReader.determineNextInResult(ownershipResult);
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found in database.",e);
        }
    }

    public void addPlaylist(NewPlaylistDTO newPlaylistDTO, String token) {
        String query = "INSERT INTO PLAYLIST SELECT ?,?, [USERNAME] FROM [USER] where [Token] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,newPlaylistDTO.getId()));
        parameters.add(SQLParameterFactory.createParameter(2,newPlaylistDTO.getName()));
        parameters.add(SQLParameterFactory.createParameter(3,token));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
    }

    public void editPlaylist(NewPlaylistDTO newPlaylistDTO, String token, int id) {
        if(checkOwnership(newPlaylistDTO.getId(), token)){
            String query = "UPDATE PLAYLIST SET [NAME] = ? WHERE PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,newPlaylistDTO.getName()));
            parameters.add(SQLParameterFactory.createParameter(2,id));
            preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        }
    }
    @Inject
    public void setResultSetReader(ResultSetReader resultSetReader) {
        this.resultSetReader = resultSetReader;
    }
    @Inject
    public void setPreparedStatementHelper(PreparedStatementHelper preparedStatementHelper) {
        this.preparedStatementHelper = preparedStatementHelper;
    }
}
