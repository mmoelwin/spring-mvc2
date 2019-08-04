package com.demo.springmvc.controller;

import com.demo.springmvc.exception.MyEntityNotFoundException;
import com.demo.springmvc.model.Category;
import com.demo.springmvc.model.Product;
import com.demo.springmvc.service.CategoryService;
import com.demo.springmvc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@Controller
public class ProductController  {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String create (Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "productForm";
    }

    @PostMapping("/product")
    public String process(@Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("categories",categoryService.findAll());
            return "productForm";
        }
        productService.create(product);

        return "redirect:/products";
    }
    @GetMapping("/products")
    public String shoeAllProducts(Model model){
        model.addAttribute("products",productService.findAll());

        return "products";
    }

    @GetMapping("/products/{id}")
    public String removeProduct(@PathVariable ("id") int id){
        productService.delete(id);

        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String update(@PathVariable("id") int id,Model model){
        this.updateId=id;
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("product",productService.findById(id));
        return "updateForm";
    }

    @PostMapping("/products/update")
    public String processUpdate(Product product){
        productService.update(product, updateId);
        return "redirect:/products";
    }

    private int updateId;

    @GetMapping("/products/details/{id}")
    public String details(@PathVariable("id") int id,Model model){
        Product product=productService.findById(id);
        logger.info("id:"+id);
        logger.info("Product:" +product);
//        if(product==null){
//            throw new EntityNotFoundException(id + " Not Found");
//        }
        model.addAttribute("product",product);
        return "productDetails";
    }


    public static Logger logger= LoggerFactory.getLogger(ProductController.class);
}
