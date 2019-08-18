package com.demo.springmvc.controller;

import com.demo.springmvc.model.Category;
import com.demo.springmvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;

@Controller
public class CategoryController  {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String create(Model model){
        model.addAttribute("category", new Category());

        return "categoryForm";
    }

    @PostMapping("/category") //register
    public String process(@Valid Category category, BindingResult result,
                          RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "categoryForm";
        }
        categoryService.create(category);
        redirectAttributes.addFlashAttribute("success1",true);
        return "redirect:/categories";
    }

                //url
    @GetMapping("/categories")
    public String showAllCategories(Model model){
        model.addAttribute("success1",model.containsAttribute("success1"));
        model.addAttribute("categories", categoryService.findAll());

        return "categories";
        //logical view name
    }

    @GetMapping("/categories/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(){
        ByteArrayInputStream bis=CategoriesPdf.categoryPdfViews(categoryService.findAll());
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=categoriesReport.pdf");
        return ResponseEntity
                .ok().contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
