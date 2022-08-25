package com.mycompany.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationInfo {
    private double lng;
    private double lat;
    private String location;
}
