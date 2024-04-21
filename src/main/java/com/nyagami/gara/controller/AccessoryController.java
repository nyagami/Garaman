package com.nyagami.gara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accessory")
public class AccessoryController {
    @GetMapping("")
    public String accessoryList(){
        return "accessoryList";
    }

    @GetMapping("/import")
    public String importAccessory(){
        return "importAccessory";
    }
}
