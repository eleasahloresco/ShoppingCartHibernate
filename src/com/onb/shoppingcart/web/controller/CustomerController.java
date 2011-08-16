package com.onb.shoppingcart.web.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.domain.User;
import com.onb.shoppingcart.service.CustomerService;
import com.onb.shoppingcart.service.ProductService;
import com.onb.shoppingcart.service.UserService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Controller
public class CustomerController {

	private UserService currentUserService;

	private CustomerService customerService;

	private ProductService productService;

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
		Product product = productService.getByName(productName);

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
