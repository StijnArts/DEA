package han.stijn.spotitube.dto;

public class SongDTO extends TrackDTO{

    public SongDTO(){

    }
    public SongDTO(int id, String titel, String performer, int afspeelduurInSecondes,
                   int playcount, String album, boolean offlineAvailable) {
        super(id, titel, performer, afspeelduurInSecondes, playcount, offlineAvailable);
        setAlbum(album);
    }
}
