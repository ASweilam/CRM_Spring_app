package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
//    private CustomerDAO customerDAO;

    @GetMapping("/list")
    public String listCustomers(Model theModel){

        //get customers from Customer Service layer.
        List<Customer> theCustomers = customerService.getCustomers();

        //add customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    private String showFormForAdd(Model theModel){

        //Create model attribute to bind data
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    private String saveCustomer(@ModelAttribute("customer") Customer customer ){

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    private String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
        Customer theCustomer = customerService.getCustomer(theId);

        theModel.addAttribute("customer",theCustomer);

        return "customer-form";
    }



    @GetMapping("/delete")
    private String deleteCustomer(@RequestParam("customerId") int theId){

        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }





}
