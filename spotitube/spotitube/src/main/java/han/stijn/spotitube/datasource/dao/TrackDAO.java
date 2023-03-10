package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import han.stijn.spotitube.dto.factory.*;
import han.stijn.spotitube.exception.*;
import jakarta.inject.*;

import java.sql.*;
import java.util.*;

public class TrackDAO {

    private PreparedStatementHelper preparedStatementHelper;
    private ResultSetReader resultSetReader;
    public ArrayList<TrackDTO> getTracks(int playlistID){
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        String query = "SELECT * FROM TRACK T WHERE T.TRACKID IN (SELECT TRACKID FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID = ?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        ResultSet trackResults = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
        processTracks(tracks,trackResults);
        return tracks;
    }

    public ArrayList<TrackDTO> getAvailableTracks(int playlistID) {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        String query = "SELECT * FROM TRACK T WHERE T.TRACKID IN (SELECT TRACKID FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID != ?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        ResultSet trackResults = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
        processTracks(tracks,trackResults);
        return tracks;
    }

    public boolean addTrackToPlaylist(int playlistID, int trackID) {
        String query = "INSERT INTO TRACK_ON_PLAYLIST VALUES (?,?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        parameters.add(SQLParameterFactory.createParameter(2,trackID));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        return true;
    }

    public boolean deleteTrackFromPlaylist(int playlistID, int trackID) {
        String query = "DELETE FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID = ? AND TRACKID = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        parameters.add(SQLParameterFactory.createParameter(2,trackID));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        return true;

    }

    public void processTracks(ArrayList<TrackDTO> tracks, ResultSet trackResults){
        try {
            while(resultSetReader.determineNextInResult(trackResults)){
                int trackID = resultSetReader.readInteger(trackResults,"TRACKID");
                String trackTitle = resultSetReader.readString(trackResults,"TRACKTITLE");
                String performer = resultSetReader.readString(trackResults,"PERFORMER");
                int trackDuration = resultSetReader.readInteger(trackResults,"TRACKDURATION");
                int trackPlaycount = resultSetReader.readInteger(trackResults,"PLAYCOUNT");
                boolean isOfflineAvailable = resultSetReader.readBoolean(trackResults,"OFFLINEAVAILABLE");
                String album = resultSetReader.readString(trackResults,"ALBUM");
                int publicationDate = resultSetReader.readInteger(trackResults,"PUBLICATIONDATE");
                String description = resultSetReader.readString(trackResults,"TRACKDESCRIPTION");
                tracks.add(TrackFactory.createTrack(trackID, trackTitle, performer, trackDuration, trackPlaycount,
                        album, publicationDate, description, isOfflineAvailable));
            }
        } catch (SQLException e) {
            throw new IncompleteQueryResultException("The query's result set was incomplete.",e);
        }
    }


    @Inject
    public void setPreparedStatementHelper(PreparedStatementHelper preparedStatementHelper) {
        this.preparedStatementHelper = preparedStatementHelper;
    }

    @Inject
    public void setResultSetReader(ResultSetReader resultSetReader) {
        this.resultSetReader = resultSetReader;
    }
}
