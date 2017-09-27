package woodspring.springx.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import woodspring.springx.entity.Customer;
import woodspring.springx.errorexception.CustomErrorType;
import woodspring.springx.service.CustomerService;

@RestController
@RequestMapping("springX")
public class CustomerController {
	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
    // -------------------Retrieve All Customers ---------------------------------------------
	 
    @GetMapping(path = "/customers", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listCustomers()
    {
        List<Customer> custList = customerService.getCustomerList();
        Gson gson = new Gson();
        // convert your list to json
        String jsonCartList = gson.toJson( custList);
        // print your generated json
        System.out.println("jsonCartList: " + jsonCartList);
        return new ResponseEntity<Object>(jsonCartList, HttpStatus.OK);
    }
    
    // -------------------Retrieve Single Custoemr------------------------------------------
 
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomer(@PathVariable("id") int id) {
        logger.info("Fetching Custoemr with id {}", id);
        Customer customer = customerService.getCustomerById( id);
        if ( customer == null) {
            logger.error("Custoemr with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        Gson gson = new Gson();
        // convert your list to json
        String jsonCustomer = gson.toJson( customer);
        return new ResponseEntity<String>(jsonCustomer, HttpStatus.OK);
    }

}
