package com.nyagami.gara.model;

import jakarta.persistence.*;
import lombok.Data;
import org.json.JSONObject;

@Entity
@Data
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String image;
    private String email;
    private String hotline;
    private String bankName;
    private String bankAccountNumber;
    private String bankAccountName;
    private String description;

    @Override
    public String toString(){
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("address", address);
        obj.put("image", image);
        obj.put("email", email);
        obj.put("hotline", hotline);
        obj.put("bankName", bankName);
        obj.put("bankAccountNumber", bankAccountNumber);
        obj.put("bankAccountName", bankAccountName);
        obj.put("description", description);
        return obj.toString();
    }
}
