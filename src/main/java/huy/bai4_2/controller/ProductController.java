package huy.bai4_2.controller;

import huy.bai4_2.model.Product;
import huy.bai4_2.model.ProductForm;
import huy.bai4_2.sevice.IProductService;
import huy.bai4_2.sevice.ProductSevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Value("${file}")
    private String file;
    private final IProductService productService= new ProductSevice();
    @GetMapping("")
    public String index(Model model){
        List<Product>products=productService.findAll();
        model.addAttribute("pro",products);
        return "/index";
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView =new ModelAndView("/create","productForm",new ProductForm());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm){
        MultipartFile multipartFile=productForm.getImage();
        String fileName=multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(productForm.getImage().getBytes(),new File(file +fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        Product product=new Product(productForm.getId(),productForm.getName(),productForm.getDescription(),fileName);
        productService.save(product);
        ModelAndView modelAndView=new ModelAndView("/create");
        modelAndView.addObject("productForm",productForm);
        modelAndView.addObject("message","Created new product successfully !");
        return modelAndView;
    }

}
