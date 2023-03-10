package han.stijn.spotitube.dto.factory;

import han.stijn.spotitube.dto.*;

public class TrackFactory {
    public static TrackDTO createTrack(int trackId, String trackTitle, String performer, int trackDuration, int trackPlaycount,
                                String album, int publicationDate, String description, boolean isOfflineAvailable) {
        if (description == null) {
            return new SongDTO(trackId, trackTitle, performer, trackDuration, trackPlaycount, album, isOfflineAvailable);
        } else {
            return new VideoDTO(trackId, trackTitle, performer, trackDuration, trackPlaycount,
                    publicationDate, description, isOfflineAvailable);
        }
    }

    @Override
    public String toString() {
        return "No attributes";
    }
}
