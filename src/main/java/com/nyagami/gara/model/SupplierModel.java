package com.nyagami.gara.model;

import jakarta.persistence.*;
import lombok.Data;

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
}
