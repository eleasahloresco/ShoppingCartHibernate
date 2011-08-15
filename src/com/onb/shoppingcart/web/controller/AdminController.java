package com.onb.shoppingcart.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.AdminService;
import com.onb.shoppingcart.service.CategoryService;
import com.onb.shoppingcart.service.ProductService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Controller
public class AdminController {
	
	private CategoryService categoryService;

	private ProductService productService;
	
	@Autowired	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	@RequestMapping(value = "/admin/admin")
	public String showAdminForm() {
		return "admin/admin";
	}
	
	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.GET)
	public String showAddCategoryForm(@ModelAttribute("categoryModel") Category category, HttpServletRequest request){
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		return "admin/addCategory";
	}
	
	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("categoryModel") Category category, BindingResult bindingResult,
			HttpServletRequest request){
		
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		if (category.getName() == null || category.getName().isEmpty()){
			bindingResult.rejectValue("name", "name.validation.error", null, "is empty");
		}
		
		if (bindingResult.hasErrors()){
			return "admin/addCategory";
		}
		
		try {
			categoryService.saveCategory(category);
		} catch (AdminServiceException e) {
			bindingResult.rejectValue("name", "name.validation.error", "already exist");
			return "admin/addCategory";
		}
		
		return "redirect:addCategory";
	}

	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.GET)
	public String showAddProductForm(@ModelAttribute("productModel") Product product, HttpServletRequest request){
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		return "admin/addProduct";
	}
	
	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("productModel") Product product, BindingResult bindingResult,
			HttpServletRequest request){
		
		List<Product> products = productService.getAllProducts();
		request.setAttribute("products", products);
		
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		if (product.getName() == null || product.getName().isEmpty()){
			bindingResult.rejectValue("name", "name.validation.error", null, "is empty");
		}
		
		if (product.getInventoryQuantity() == null){
			bindingResult.rejectValue("inventoryQuantity", "inventoryQuantity.validation.error", null, "is empty");
		}
		
//		if(product.getInventoryQuantity() == BigDecimal.ZERO){
//			bindingResult.rejectValue("inventoryQuantity", "inventoryQuantity.validation.error",  "Value cannot be zero");
//		}
		
		if (product.getUnitPrice() == null ){
			bindingResult.rejectValue("unitPrice", "unitPrice.validation.error", null, "is empty");
		}
		
//		if(product.getUnitPrice() == BigDecimal.ZERO){
//			bindingResult.rejectValue("unitPrice", "unitPrice.validation.error",  "Value cannot be zero");
//		}
		
		if (bindingResult.hasErrors()){
			return "admin/addProduct";
		}
		
		Long selected= new Long(request.getParameter("category.id"));
		Category category = categoryService.getCategory(selected);
		product.setCategory(category);
		
		try {
			productService.saveProduct(product);
		} catch (AdminServiceException e) {
			bindingResult.rejectValue("name", "name.validation.error", "already exist");
			return "admin/addProduct";
		}
		
		return "redirect:addProduct";
	}
	
	
}
