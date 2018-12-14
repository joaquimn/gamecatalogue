package domain;

public class User {

    private String userId;
    private String userName;
    private String userEmail;
    private String postalCode;
    private City userCity;
    private int flgActive;
    private String picture;
    private String password;
    private String createdAt;

    //constructor
    public User(String userId, String userName, String userEmail, String postalCode, City userCity,int flgActive, String picture, String password, String createdAt) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.postalCode = postalCode;
        this.userCity = userCity;
        this.flgActive = flgActive;
        this.picture = picture;
        this.password = password;
        this.createdAt = createdAt;
    }

    public User(){ }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFirstName() {
        return userName.split(" ")[0];
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public City getUserCity() {
        return userCity;
    }

    public int getFlgActive() {
        return flgActive;
    }

    public String getPicture() { return picture; }

    public String getPassword() { return password; }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtDate() {
        return createdAt.split(" ")[0];
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(City userCity) {
        this.userCity = userCity;
    }

    public void setFlgActive(int flgActive) {
        this.flgActive = flgActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

