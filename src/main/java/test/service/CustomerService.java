/**
 * 
 */
package test.service;

import de.brueckner.mms.businessdefact.data.Customer;
import de.brueckner.mms.businessdefact.data.CustomerOrder;
import de.brueckner.mms.businessdefact.data.CustomerOrderItem;
import de.brueckner.mms.businessdefact.data.CustomerOrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author RaresI
 *
 */
public class CustomerService {

	private List<Customer> customers = new ArrayList<Customer>();

	public CustomerService(){
		Customer c = new Customer();
		c.setId(1);
		c.setKey("customer1");
		c.setName("Biofilm");
		c.setAddress("Columbia,..");
		c.setCreationalDate(new Date());
		buildCOfor(c);
		customers.add(c);
		
		Customer c2 = new Customer();
		c2.setId(2);
		c2.setKey("customer2");
		c2.setName("BST");
		c2.setAddress("Romania, Baba Novac");
		c2.setCreationalDate(new Date());
		buildCOfor(c2);
		customers.add(c2);
		
	}
	
	private void buildCOfor(Customer c){
		List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		CustomerOrder co = new CustomerOrder();
		co.setCustomer(c);
		co.setId(c.getId()*10+1);
		co.setKey(c.getKey()+"_co1");
		co.setCreationalDate(new Date());
		co.setStatus(CustomerOrderStatus.PLANNED);
		buildCOIfor(co);
		customerOrders.add(co);
		
		CustomerOrder co2 = new CustomerOrder();
		co2.setCustomer(c);
		co2.setId(c.getId()*10+2);
		co2.setKey(c.getKey()+"_co2");
		co2.setCreationalDate(new Date());
		co2.setStatus(CustomerOrderStatus.INITIAL);
		buildCOIfor(co2);
		customerOrders.add(co2);
		
		CustomerOrder co3 = new CustomerOrder();
		co3.setCustomer(c);
		co3.setId(c.getId()*10+3);
		co3.setKey(c.getKey()+"_co3");
		co3.setCreationalDate(new Date());
		co3.setStatus(CustomerOrderStatus.PLANNED);
		buildCOIfor(co3);
		customerOrders.add(co3);
		
		c.setCustomerOrders(customerOrders);
	}
	
	private void buildCOIfor(CustomerOrder co){
		List<CustomerOrderItem> customerOrderItems = new ArrayList<CustomerOrderItem>();
		
		CustomerOrderItem coi1 = new CustomerOrderItem();
		coi1.setCustomerOrder(co);
		coi1.setId(co.getId()*10+1);
		coi1.setKey(co.getKey()+"_coi1");
		coi1.setCreationalDate(new Date());
		coi1.setStatus(CustomerOrderStatus.PLANNED);
		coi1.setProductKey("product1");
		customerOrderItems.add(coi1);
		
		CustomerOrderItem coi2 = new CustomerOrderItem();
		coi2.setCustomerOrder(co);
		coi2.setId(co.getId()*10+2);
		coi2.setKey(co.getKey()+"_coi2");
		coi2.setCreationalDate(new Date());
		coi2.setStatus(CustomerOrderStatus.INITIAL);
		coi2.setProductKey("product1");
		customerOrderItems.add(coi2);
		
		CustomerOrderItem coi3 = new CustomerOrderItem();
		coi3.setCustomerOrder(co);
		coi3.setId(co.getId()*10+3);
		coi3.setKey(co.getKey()+"_coi3");
		coi3.setCreationalDate(new Date());
		coi3.setStatus(CustomerOrderStatus.PLANNED);
		coi3.setProductKey("product1");
		customerOrderItems.add(coi3);
		
		co.setCustomerOrderItems(customerOrderItems);
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

}
