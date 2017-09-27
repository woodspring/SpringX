package woodspring.springx.service;

import java.util.List;

import woodspring.springx.entity.Customer;

public interface CustomerServiceIntf {
	List<Customer> getCustomerList();
	Customer getCustomerById(int custId);

}
