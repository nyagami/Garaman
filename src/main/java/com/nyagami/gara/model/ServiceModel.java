package com.nyagami.gara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.json.JSONObject;

@Entity
@Data
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long price;
    private String unit;
    @Override
    public String toString(){
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("description", description);
        obj.put("image", image);
        obj.put("price", price);
        obj.put("unit", unit);
        return obj.toString();
    }
}
