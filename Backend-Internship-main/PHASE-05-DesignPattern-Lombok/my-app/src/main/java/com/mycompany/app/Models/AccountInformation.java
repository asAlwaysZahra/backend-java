package com.mycompany.app.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
