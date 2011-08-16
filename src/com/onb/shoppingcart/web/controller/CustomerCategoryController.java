package com.onb.shoppingcart.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.CategoryService;

@Controller
public class CustomerCategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@RequestMapping(value = "/customer/customer")
	public String showCustomerForm(HttpServletRequest request) {
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		return "customer/customer";
	}
	
}
