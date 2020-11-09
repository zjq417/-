package com.controller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/testJson")
    public String testJson() {
        String name = "ZJQ";
        return name;
    }

    @PostMapping("/testPost")
    public String testPost() {
        return "testPost";
    }
}
