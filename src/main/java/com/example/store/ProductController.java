package com.example.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @GetMapping("/product")
    public String list(){
        return "/product/list";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id) {
        return "/product/detail/"+id;
    }

    @PostMapping("/product/save")
    public String save() {
        return "redirect:/";
    }

    @GetMapping("/product/save-form")
    public String saveForm() {
        return "/product/save-form";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable Integer id){
        return "redirect:/product/"+id;
    }

    @GetMapping("/product/update-form")
    public String updateForm(){
        return "/product/update-form";
    }

    @PostMapping("product/{id}/delete")
    public String delete(@PathVariable Integer id){
        return "redirect:/";
    }

    @GetMapping("/")
    public String index() {
        return "/index";
    }


}
