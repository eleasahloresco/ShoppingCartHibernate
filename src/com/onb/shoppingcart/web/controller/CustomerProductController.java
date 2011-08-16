package com.onb.shoppingcart.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.ProductService;

@Controller
public class CustomerProductController {

	private ProductService productService;
	
	@Autowired	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value = "/customer/showProducts")
	public String productsForm(@RequestParam("categoryNumber") Long categoryNumber, HttpServletRequest request){
		List<Product> products = productService.getAllProductsWithQuantityGreaterThanZero(categoryNumber);
		request.setAttribute("products", products);
		
		return "customer/products";
	}
	
	@RequestMapping(value = "/customer/setQuantity",  method = RequestMethod.GET)
	public String setQuantity(@ModelAttribute("orderDetailModel") OrderDetail orderDetail,
			@RequestParam("productNumber") Long productNumber,
			HttpServletRequest request){

		Product product = productService.get(productNumber);
		request.setAttribute("product", product);
		

		return "customer/setQuantity";
	}
	
	@RequestMapping(value = "/customer/setQuantity",  method = RequestMethod.POST)
	public String Quantity(@ModelAttribute("orderDetailModel") OrderDetail orderDetail, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam("product.name") String productName, 
			Principal principal){
		Product product = productService.getByName(productName);
		request.setAttribute("product", product);
		
		Long categoryNumber = product.getCategory().getId();
		List<Product> products = productService.getByCategory(categoryNumber);
		request.setAttribute("products", products);
		
		if (orderDetail.getQuantity() == null){
			bindingResult.rejectValue("quantity", "quantity.validation.error", null, "is empty");
		}
		
		if(product.getInventoryQuantity() < orderDetail.getQuantity()){
			bindingResult.rejectValue("quantity", "quantity.validation.error", 
					"quantity should be less than " + product.getInventoryQuantity());
		}
		
		if(orderDetail.getQuantity() < 0){
			bindingResult.rejectValue("quantity", "quantity.validation.error", 
					"quantity should be greater than zero");
		}
		
		if (bindingResult.hasErrors()){
			return "customer/setQuantity";
		}
		
		orderDetail.setUnitPrice(product.getUnitPrice());
		orderDetail.setProduct(product);
		
		HttpSession session = request.getSession();
		
		Order order = (Order)session.getAttribute("order");
		if(order == null){
			Order newOrder = new Order();
			newOrder.updateListOfOrderDetails(orderDetail);
			session.setAttribute("order", newOrder);
		}else{
			order.updateListOfOrderDetails(orderDetail);
			order.setAmount(order.getAmount());
			session.setAttribute("order", order);
		}
			
		return "customer/products";
	}
}
