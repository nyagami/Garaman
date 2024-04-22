package com.nyagami.gara.controller;

import com.nyagami.gara.data.AccessoryOrderStatus;
import com.nyagami.gara.model.AccessoryModel;
import com.nyagami.gara.model.AccessoryOrderModel;
import com.nyagami.gara.model.SupplierModel;
import com.nyagami.gara.repository.AccessoryOrderRepository;
import com.nyagami.gara.repository.AccessoryRepository;
import com.nyagami.gara.repository.ImageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/accessory")
public class AccessoryController {
    @Data
    private class AccessoryOrderForm {
        private  List<AccessoryOrderModel> orders;
        private Long supplierId;
    }
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private AccessoryOrderRepository accessoryOrderRepository;
    private final ImageRepository imageRepository = ImageRepository.getInstance();
    private Page<AccessoryModel> getAccessories(Integer page, String keyword){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return accessoryRepository.findByNameOrProductCodeContaining(keyword, keyword, pageable);
    }
    @GetMapping("")
    public String accessoryList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String keyword,
            Model model
    ){
        Page<AccessoryModel> accessories = getAccessories(page, keyword);
        model.addAttribute("accessories", accessories);
        model.addAttribute("pageTitle", "Linh kiện");
        model.addAttribute("accessory", new AccessoryModel());
        model.addAttribute("keyword", keyword);
        return "accessory/list";
    }

    @PostMapping("")
    public String updateAccessory(
            @ModelAttribute AccessoryModel accessory,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "delete", required = false) Boolean delete,
            Model model
    ){
        if(delete == null){
            delete = false;
        }
        if(delete){
            accessoryRepository.delete(accessory);
        }else{
            String imageUrl = accessory.getImage();
            if(!file.isEmpty()){
                try {
                    if(!file.isEmpty()){
                        imageUrl = imageRepository.uploadFile(file);
                    }
                } catch (IOException ignored) {}
            }
            if(imageUrl == null || imageUrl.isBlank()){
                imageUrl = "/image/default_accessory.png";
            }
            accessory.setImage(imageUrl);
            accessoryRepository.save(accessory);
        }
        model.addAttribute("accessories", getAccessories(page, keyword));
        model.addAttribute("pageTitle", "Linh kiện");
        model.addAttribute("supplier", new SupplierModel());
        model.addAttribute("keyword", keyword);
        return "accessory/list";
    }

    @GetMapping("/import")
    public String importAccessory(
            @RequestParam(defaultValue = "0") Integer page,
            Model model
    ){
        model.addAttribute("pageTitle", "Nhập linh kiện");
        return "accessory/import";
    }

    @PostMapping("/import")
    public String addOrders(
            @ModelAttribute AccessoryOrderForm form,
            Model model
    ) {
        System.out.println(form.orders);
        System.out.println(form.supplierId);
        model.addAttribute("pageTitle", "Nhập linh kiện");
        return "accessory/import";
    }
}
