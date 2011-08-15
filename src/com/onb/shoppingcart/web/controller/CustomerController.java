package com.onb.shoppingcart.web.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
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

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.domain.User;
import com.onb.shoppingcart.service.CategoryService;
import com.onb.shoppingcart.service.CustomerService;
import com.onb.shoppingcart.service.ProductService;
import com.onb.shoppingcart.service.UserService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Controller
public class CustomerController {

	private CategoryService categoryService;
	
	private ProductService productService;
	
	private UserService currentUserService;
	
	private CustomerService customerService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Autowired	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCurrentUserService(UserService currentUserService) {
		this.currentUserService = currentUserService;
	}
	
	@Autowired	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/customer/customer")
	public String showCustomerForm(HttpServletRequest request) {
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		return "customer/customer";
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

		Product product = productService.getProduct(productNumber);
		request.setAttribute("product", product);
		

		return "customer/setQuantity";
	}

	@RequestMapping(value = "/customer/setQuantity",  method = RequestMethod.POST)
	public String Quantity(@ModelAttribute("orderDetailModel") OrderDetail orderDetail, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam("product.name") String productName, 
			Principal principal){
		Product product = productService.getProductByName(productName);
		request.setAttribute("product", product);
		
		Long categoryNumber = product.getCategory().getId();
		List<Product> products = productService.getProductByCategory(categoryNumber);
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
	
	private User getCurrentUser(Principal principal){
		User currentUser = new User();
		String username = principal.getName();
		currentUser.setUsername(username);
		
		return currentUser;
	}
	
	@RequestMapping(value = "/customer/shoppingCart")
	public String viewShoppingCart(HttpServletRequest request){
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		BigDecimal totalPrice = order.computeToTalAmount();
		orderDetails = order.getOrderDetails();
		
		if(orderDetails.isEmpty()){
			return "redirect: customer";
		}
		
		request.setAttribute("orderDetails", orderDetails);
		request.setAttribute("totalPrice", totalPrice);

		return "customer/shoppingCart";
	}
	
	@RequestMapping(value = "/customer/remove")
	public String removeOrderDetail(HttpServletRequest request,
			@RequestParam("orderDetail.product.name") String productName){
		Product product = productService.getProductByName(productName);
	
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		List<OrderDetail> orderDetails = order.getOrderDetails();
		
		for (OrderDetail orderDetail : orderDetails) {
			if(orderDetail.getProduct().equals(product)){
				order.removeOrderDetail(orderDetail);
			}
		}
		
		order.setAmount(order.computeToTalAmount());
		session.setAttribute("order", order);
		
		return "redirect:shoppingCart";
	}
	
	@RequestMapping(value = "/customer/checkOut")
	public String checkOut(HttpServletRequest request, Principal principal){
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		
		User user = getCurrentUser(principal);
		User newUser = currentUserService.getUserByUsername(user.getUsername());
		
		order.setUser(newUser);
		order.setAmount(order.computeToTalAmount());
		try {
			customerService.saveOrder(order);
		} catch (AdminServiceException e) {
			return "redirect:shoppingCart";
		}
			
		session.removeAttribute("order");
		
		return "redirect:customer";
	}
	
}
