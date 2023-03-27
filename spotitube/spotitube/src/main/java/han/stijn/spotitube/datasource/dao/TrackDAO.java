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
    public TracksDTO getTracks(int playlistID){
        var tracks = new ArrayList<TrackDTO>();
        var query = "SELECT * FROM TRACK T WHERE T.TRACKID IN (SELECT TRACKID FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID = ?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        ResultSet trackResults = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
        processTracks(tracks,trackResults);
        return new TracksDTO(tracks);
    }

    public TracksDTO getAvailableTracks(int playlistID) {
        var tracks = new ArrayList<TrackDTO>();
        var query = "SELECT * FROM TRACK T WHERE T.TRACKID IN (SELECT TRACKID FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID != ?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        ResultSet trackResults = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
        processTracks(tracks,trackResults);
        return new TracksDTO(tracks);
    }

    public boolean addTrackToPlaylist(int playlistID, int trackID) {
        var query = "INSERT INTO TRACK_ON_PLAYLIST VALUES (?,?)";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        parameters.add(SQLParameterFactory.createParameter(2,trackID));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        return true;
    }

    public boolean deleteTrackFromPlaylist(int playlistID, int trackID) {
        var query = "DELETE FROM TRACK_ON_PLAYLIST WHERE PLAYLISTID = ? AND TRACKID = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,playlistID));
        parameters.add(SQLParameterFactory.createParameter(2,trackID));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        return true;

    }

    public void processTracks(ArrayList<TrackDTO> tracks, ResultSet trackResults){
        try {
            while(resultSetReader.determineNextInResult(trackResults)){
                var trackID = resultSetReader.readInteger(trackResults,"TRACKID");
                var trackTitle = resultSetReader.readString(trackResults,"TRACKTITLE");
                var performer = resultSetReader.readString(trackResults,"PERFORMER");
                var trackDuration = resultSetReader.readInteger(trackResults,"TRACKDURATION");
                var trackPlaycount = resultSetReader.readInteger(trackResults,"PLAYCOUNT");
                var isOfflineAvailable = resultSetReader.readBoolean(trackResults,"OFFLINEAVAILABLE");
                var album = resultSetReader.readString(trackResults,"ALBUM");
                var publicationDate = resultSetReader.readInteger(trackResults,"PUBLICATIONDATE");
                var description = resultSetReader.readString(trackResults,"TRACKDESCRIPTION");
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
