package domain;

public class Game {

    private String gameId;
    private String gameName;
    private String description;
    private String cover;
    private boolean flgActive;
    private String released;
    private String createdAt;
    private Genre genre;
    private Platform platform;
    private Producer producer;
    private Region region;
    private Status status;

    public Game(String gameId, String gameName, String description, String cover, boolean flgActive, String released, String createdAt, Genre genre, Platform platform, Producer producer, Region region, Status status) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.description = description;
        this.cover = cover;
        this.flgActive = flgActive;
        this.released = released;
        this.createdAt = createdAt;
        this.genre = genre;
        this.platform = platform;
        this.producer = producer;
        this.region = region;
        this.status = status;
    }

    public Game(){}

    public Game(String gameId, String gameName, String cover) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.cover = cover;
    }

    public Game(String gameId, String gameName, String cover, Platform platform) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.cover = cover;
        this.platform = platform;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isFlgActive() {
        return flgActive;
    }

    public void setFlgActive(boolean flgActive) {
        this.flgActive = flgActive;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

