package test;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.tree.LinkTree;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import test.ganttchart.GanttChart;
import test.service.CustomerService;
import de.brueckner.mms.businessdefact.data.Customer;
import de.brueckner.mms.businessdefact.data.CustomerOrder;
import de.brueckner.mms.businessdefact.data.CustomerOrderItem;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		// add gantt chart
		add(new GanttChart("ganttChart"));

		// add(new Label("version",
		// getApplication().getFrameworkSettings().getVersion()));
		MarkupContainer container = new WebMarkupContainer("container");
		add(container);
		LinkTree tree = new LinkTree("tree", createTreeModel());
		container.add(tree);
		tree.getTreeState().collapseAll();

	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavaScriptReference("lib/jquery-1.8.0.min.js");
		response.renderJavaScriptReference("lib/jquery-ui-1.8.23.custom.min.js");
		response.renderJavaScriptReference("lib/date.js");
		response.renderJavaScriptReference("jquery.ganttView.js");
		// css
		response.renderCSSReference("lib/jquery-ui-1.8.23.custom.css");
		response.renderCSSReference("jquery.ganttView.css");
	}

	/**
	 * Creates the model that feeds the tree.
	 * 
	 * @return New instance of tree model.
	 */
	protected TreeModel createTreeModel() {
		TreeModel model = null;
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");

		CustomerService customerService = new CustomerService();
		List<Customer> customers = customerService.getCustomers();
		for (Customer cust : customers) {
			for (CustomerOrder co : cust.getCustomerOrders()) {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(
						cust.getKey());
				rootNode.add(child);
				for (CustomerOrderItem coi : co.getCustomerOrderItems()) {
					child.add(new DefaultMutableTreeNode(coi.getKey()));
				}
			}
		}

		model = new DefaultTreeModel(rootNode);
		return model;
	}

	private void add(DefaultMutableTreeNode parent, List<Object> sub) {
		for (Object obj : sub) {
			if (obj instanceof List) {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(
						"subtree...");
				parent.add(child);
				add(child, (List<Object>) obj);
			} else {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(
						obj.toString());
				parent.add(child);
			}
		}
	}
}
