package han.stijn.spotitube.dto;

public class TrackDTO {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private int playcount;
    private boolean offlineAvailable;
    private int publicationDate;
    private String description;

    @Override
    public String toString() {
        return "Id: "+id+". Title: "+title+". Performer: "+performer+". Duration: "+duration+". Playcount: "+playcount+
                ". publicationDate: "+publicationDate+". Descriptoin: "+description+". Offline Available: "+offlineAvailable;
    }
    public TrackDTO(){}
    public TrackDTO(int id, String title, String performer, int duration,
                    int playcount, boolean offlineAvailable){
        this.id=id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String album;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
