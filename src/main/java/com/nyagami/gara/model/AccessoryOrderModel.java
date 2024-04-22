package com.nyagami.gara.model;

import com.nyagami.gara.data.AccessoryOrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class AccessoryOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AccessoryModel accessory;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private SupplierModel supplier;
    @OneToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private AccessoryOrderInvoiceModel invoice; // hoá đơn
    @OneToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private AccessoryOrderNoteModel note; // phiếu nhập
    private Integer quantity;
    private Long importedPrice;
    private Date createAt;
    private AccessoryOrderStatus status;
    private String description;
}
