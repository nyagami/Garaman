package com.nyagami.gara.controller;

import com.nyagami.gara.model.ServiceModel;
import com.nyagami.gara.repository.ImageRepository;
import com.nyagami.gara.repository.ServiceRepository;
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

@Controller
@RequestMapping("service")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    private final ImageRepository imageRepository = ImageRepository.getInstance();

    private Page<ServiceModel> getServices(Integer page, String keyword){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return serviceRepository.findByNameContaining(keyword, pageable);
    }
    @GetMapping("")
    public String list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String keyword,
            Model model
    ){
        Page<ServiceModel> services = getServices(page, keyword);
        model.addAttribute("services", services);
        model.addAttribute("pageTitle", "Dịch vụ");
        model.addAttribute("service", new ServiceModel());
        model.addAttribute("keyword", keyword);
        return "service/list";
    }

    @PostMapping("")
    public String update(
            @ModelAttribute ServiceModel service,
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
            serviceRepository.delete(service);
        }else{
            String imageUrl = service.getImage();
            if(!file.isEmpty()){
                try {
                    if(!file.isEmpty()){
                        imageUrl = imageRepository.uploadFile(file);
                    }
                } catch (IOException ignored) {}
            }
            if(imageUrl == null || imageUrl.isBlank()){
                imageUrl = "/image/default_service.jpg";
            }
            service.setImage(imageUrl);
            serviceRepository.save(service);
        }
        model.addAttribute("services", getServices(page, keyword));
        model.addAttribute("pageTitle", "Dịch vụ");
        model.addAttribute("service", new ServiceModel());
        model.addAttribute("keyword", keyword);
        return "service/list";
    }
}
