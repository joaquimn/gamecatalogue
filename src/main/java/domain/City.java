package domain;

public class City {
    private Province province;
    private int cityId;
    private String cityName;

    public City(){}

    public City(Province province, int cityId, String cityName) {
        this.province = province;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Province getProvince() {
        return province;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}