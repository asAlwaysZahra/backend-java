package com.mycompany.app;

public class AccountInformation {
    private int userStatus;
    private String profilePicture;
    private String coverPicture;
    private boolean enablefollowme;
    private boolean sendmenotifications;
    private boolean sendTextmessages;
    private boolean enabletagging;
    private String createdAt;
    private String updatedAt;
    private LocationInfo liveLocationInfo;

    public AccountInformation(int userStatus, String profilePicture, String coverPicture, boolean enablefollowme,
                              boolean sendmenotifications, boolean sendTextmessages, boolean enabletagging,
                              String createdAt, String updatedAt, LocationInfo liveLocationInfo) {
        this.userStatus = userStatus;
        this.profilePicture = profilePicture;
        this.coverPicture = coverPicture;
        this.enablefollowme = enablefollowme;
        this.sendmenotifications = sendmenotifications;
        this.sendTextmessages = sendTextmessages;
        this.enabletagging = enabletagging;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.liveLocationInfo = liveLocationInfo;
    }

    // Getters and Setters ================================================

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public boolean isEnablefollowme() {
        return enablefollowme;
    }

    public void setEnablefollowme(boolean enablefollowme) {
        this.enablefollowme = enablefollowme;
    }

    public boolean isSendmenotifications() {
        return sendmenotifications;
    }

    public void setSendmenotifications(boolean sendmenotifications) {
        this.sendmenotifications = sendmenotifications;
    }

    public boolean isSendTextmessages() {
        return sendTextmessages;
    }

    public void setSendTextmessages(boolean sendTextmessages) {
        this.sendTextmessages = sendTextmessages;
    }

    public boolean isEnabletagging() {
        return enabletagging;
    }

    public void setEnabletagging(boolean enabletagging) {
        this.enabletagging = enabletagging;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocationInfo getLiveLocation() {
        return liveLocationInfo;
    }

    public void setLiveLocation(LocationInfo liveLocationInfo) {
        this.liveLocationInfo = liveLocationInfo;
    }
}
