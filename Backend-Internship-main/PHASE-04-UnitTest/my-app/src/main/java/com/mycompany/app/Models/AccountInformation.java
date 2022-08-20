package com.mycompany.app.Models;

public class AccountInformation {
    private final int userStatus;
    private final String profilePicture;
    private final String coverPicture;
    private final boolean enablefollowme;
    private final boolean sendmenotifications;
    private final boolean sendTextmessages;
    private final boolean enabletagging;
    private final String createdAt;
    private final String updatedAt;
    private final LocationInfo liveLocationInfo;

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

    @Override
    public String toString() {
        return "AccountInformation{" +
                "userStatus=" + userStatus +
                ", profilePicture='" + profilePicture + '\'' +
                ", coverPicture='" + coverPicture + '\'' +
                ", enablefollowme=" + enablefollowme +
                ", sendmenotifications=" + sendmenotifications +
                ", sendTextmessages=" + sendTextmessages +
                ", enabletagging=" + enabletagging +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", liveLocationInfo=" + liveLocationInfo +
                '}';
    }

    // Getters and Setters ================================================

    public int getUserStatus() {
        return userStatus;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public boolean isEnablefollowme() {
        return enablefollowme;
    }

    public boolean isSendmenotifications() {
        return sendmenotifications;
    }

    public boolean isSendTextmessages() {
        return sendTextmessages;
    }

    public boolean isEnabletagging() {
        return enabletagging;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public LocationInfo getLiveLocation() {
        return liveLocationInfo;
    }
}
