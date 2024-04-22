package com.nyagami.gara.controller;

import com.nyagami.gara.model.AccessoryModel;
import com.nyagami.gara.model.SupplierModel;
import com.nyagami.gara.repository.AccessoryRepository;
import com.nyagami.gara.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/accessories")
    public List<AccessoryModel> getAccessories(
            @RequestParam(defaultValue = "") String keyword
    ){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<AccessoryModel> page = accessoryRepository.findByNameOrProductCodeContaining(keyword, keyword, pageable);
        return page.toList();
    }

    @GetMapping("/suppliers")
    public List<SupplierModel> getSuppliers(
            @RequestParam(defaultValue = "") String keyword
    ){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<SupplierModel> page = supplierRepository.findByNameContaining(keyword, pageable);
        return page.toList();
    }
}
