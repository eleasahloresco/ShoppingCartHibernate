package com.onb.shoppingcart.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.CategoryService;
import com.onb.shoppingcart.service.ProductService;

@Controller
public class CustomerController {

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



	@RequestMapping(value = "/customer/customer")
	public String showCustomerForm(HttpServletRequest request) {
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		return "customer/customer";
	}
	
	
	@RequestMapping(value = "/customer/showProducts")
	public String productsForm(@RequestParam("categoryNumber") Long categoryNumber, HttpServletRequest request){
		List<Product> products = productService.getByCategory(categoryNumber);
		request.setAttribute("products", products);
		
		return "customer/products";
	}
}
