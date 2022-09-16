package com.mycompany.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Antique implements Serializable {
    private String id;
    private String name;
    private double price;
}
