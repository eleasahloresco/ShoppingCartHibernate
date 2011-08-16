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
import com.onb.shoppingcart.service.CategoryService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Controller
public class AdminCategoryController {

	private CategoryService categoryService;
	
	@Autowired	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
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
}
