package domain;

public class Platform {
    private String platformId;
    private String platformName;
    private String platformLogo;

    public Platform(String platformId, String platformName, String platformLogo) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.platformLogo = platformLogo;
    }

    public Platform(){}

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformLogo() {
        return platformLogo;
    }

    public void setPlatformLogo(String platformLogo) {
        this.platformLogo = platformLogo;
    }
}
