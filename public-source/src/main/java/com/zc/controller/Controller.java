package com.zc.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Integer count = 0;


    @GetMapping("/add")
    public void add(){
        count++;
        System.out.println("------"+count);
    }


    @GetMapping("/clean")
    public void clean(){
        count = 0;
        System.out.println("重制成功");
    }
}
