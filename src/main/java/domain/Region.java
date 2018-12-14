package domain;

public class Region {
    String regionId;
    String regionName;

    public Region(String regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Region(){}

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
