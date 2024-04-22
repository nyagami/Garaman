package com.nyagami.gara.model;

import jakarta.persistence.*;
import lombok.Data;
import org.json.JSONObject;

@Entity
@Data
public class AccessoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long price;
    private String unit;
    private String productCode;
    private Integer quantity;
    @Override
    public String toString(){
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("description", description);
        obj.put("image", image);
        obj.put("price", price);
        obj.put("unit", unit);
        obj.put("productCode", productCode);
        obj.put("quantity", quantity);
        return obj.toString();
    }
}
