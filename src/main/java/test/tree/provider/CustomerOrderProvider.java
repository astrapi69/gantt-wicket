package test.tree.provider;

import de.brueckner.mms.businessdefact.data.Customer;
import de.brueckner.mms.businessdefact.data.CustomerOrder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import test.service.CustomerService;
import wickettree.ITreeProvider;

import java.io.Serializable;
import java.util.*;

/**
 * User: Mircea Gaceanu
 */
public class CustomerOrderProvider<T extends Serializable> implements ITreeProvider<T> {

    private static final long serialVersionUID = 1L;

    /**
     * All root {@link CustomerOrder}s.
     */
    private List<T> roots = new ArrayList<T>();

    private transient CustomerService customerService = new CustomerService();


    public CustomerOrderProvider() {
        for (Customer customer : customerService.getCustomers()) {
            for (CustomerOrder customerOrder : customer.getCustomerOrders()) {
                roots.add((T) customerOrder);
            }
        }
    }

    /**
     * Nothing to do.
     */
    public void detach() {
    }

    public Iterator<T> getRoots() {
        return roots.iterator();
    }

    public boolean hasChildren(T node) {
        if (node instanceof CustomerOrder) {
            CustomerOrder customerOrder = (CustomerOrder) node;
            return customerOrder.getCustomerOrderItems() != null && !customerOrder.getCustomerOrderItems().isEmpty();
        }
        return false;
    }

    public Iterator<T> getChildren(final T node) {
        if (node instanceof CustomerOrder) {
            CustomerOrder customerOrder = (CustomerOrder) node;
            return (Iterator<T>) customerOrder.getCustomerOrderItems().iterator();
        }
        return Collections.EMPTY_LIST.iterator();
    }

    /**
     * Creates a model.
     */
    public IModel<T> model(T node) {
        return new Model<T>(node);
    }

}
