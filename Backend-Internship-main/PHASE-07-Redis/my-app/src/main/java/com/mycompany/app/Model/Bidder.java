package com.mycompany.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Bidder implements Serializable {
    private String id;
    private String name;
    private double budget;
}
