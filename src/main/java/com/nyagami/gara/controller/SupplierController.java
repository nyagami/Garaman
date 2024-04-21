package com.nyagami.gara.controller;

import com.nyagami.gara.model.SupplierModel;
import com.nyagami.gara.repository.ImageRepository;
import com.nyagami.gara.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;
    private final ImageRepository imageRepository = ImageRepository.getInstance();
    private List<SupplierModel> getSuppliers(Integer page, String keyword){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        List<SupplierModel> suppliers = supplierRepository.findByName(keyword, pageable);
        return supplierRepository.findByName(keyword, pageable);
    }
    @GetMapping("")
    public String supplierList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String keyword,
            Model model
    ){
        List<SupplierModel> suppliers = getSuppliers(page, keyword);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("pageTitle", "Nhà cung cấp");
        model.addAttribute("supplier", new SupplierModel());
        return "supplier/list";
    }

    @PostMapping("")
    public String createSupplier(
            @ModelAttribute SupplierModel supplier,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        String imageUrl = imageRepository.uploadFile(file);
        System.out.println(imageUrl);
        supplier.setImage(imageUrl);
//        supplierRepository.save(supplier);
        List<SupplierModel> suppliers = getSuppliers(page, keyword);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("pageTitle", "Nhà cung cấp");
        model.addAttribute("supplier", new SupplierModel());
        return "supplier/list";
    }
}
