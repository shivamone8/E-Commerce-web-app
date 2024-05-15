package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "AdminHome";
    }

    //getting all the categories
    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "CategoriesAdd";
    }

    //binding the category data that is submitted from the view
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String UpdateCat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent())
        {
            model.addAttribute("category", category.get());
        return "CategoriesAdd";
        }
        else {
            return "404";
        }
    }

    //Product Section
@GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "Products";
}

    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "ProductsADD";
    }

    @PostMapping("/admin/products/add")
    public String productAdd(@ModelAttribute ("productDTO") ProductDTO productDTO, @RequestParam("productImage")MultipartFile file, @RequestParam("imgName") String imgName) throws IOException{
        Product product= new Product();
        product.setProduct_id(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
        Files.write(fileNameAndPath, file.getBytes());
        }
        else{
            imageUUID= imgName;
                    }
product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String productsUpdate(@PathVariable int id, Model model ){
Product product= productService.getProductById(id).get();
ProductDTO productDTO= new ProductDTO();
productDTO.setId(product.getProduct_id());
productDTO.setName(product.getName());
productDTO.setCategoryId(product.getCategory().getId());
productDTO.setPrice(product.getPrice());
productDTO.setWeight(product.getWeight());
productDTO.setDescription(product.getDescription());
productDTO.setImageName(product.getImageName());
model.addAttribute("categories", categoryService.getAllCategory());
model.addAttribute("productDTO", productDTO);

        return "ProductsADD";
    }




}

