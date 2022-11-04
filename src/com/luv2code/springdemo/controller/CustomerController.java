package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

//	need to inject the customerService

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model) {

//		get customers from customerService
		List<Customer> customers = customerService.getCustomers();

//		add the customers to spring model
		model.addAttribute("customers", customers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

//		create model attribute to bind form data
		Customer customer = new Customer();

		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

//		save customer using our service
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}

	@RequestMapping("showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int customerId, Model model) {

//		get the customer from service
		Customer customer = customerService.getCustomer(customerId);

//		set customer as model attribute to pre-populate the form
		model.addAttribute("customer", customer);

//		send over to our form
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId, Model model) {

//		delete the customer
		customerService.deleteCustomer(customerId);

		return "redirect:/customer/list";

	}

}
