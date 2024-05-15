package com.example.demo.controller;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/" , "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "Index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopById(Model model, @PathVariable int id){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("category", categoryService.getCategoryById(id));
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "CatWiseProd";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String ShopProduct(Model model, @PathVariable int id){
        model.addAttribute("cartCount", GlobalData.cart.size());

        model.addAttribute("product", productService.getProductById(id).get());
        return "ViewProduct";
    }

    @GetMapping("/ShowLoginPage")
    public  String LoginPage(){
        return "LoginPage";
    }

@GetMapping("/AccessDenied")
public String AccessDenied(){
        return "Access-Denied";
}



}
