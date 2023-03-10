package han.stijn.spotitube.dto;

public class VideoDTO extends TrackDTO{

    public VideoDTO(int id, String title, String performer, int duration, int playcount, int publicationDate,
                    String description, boolean offlineAvailable) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        setDescription(description);
        setPublicationDate(publicationDate);
    }

    public VideoDTO(){

    }

}
