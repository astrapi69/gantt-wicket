package test;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import test.ganttchart.GanttChart;
import test.tree.content.Content;
import test.tree.content.FolderContent;
import test.tree.provider.CustomerOrderProvider;
import wickettree.AbstractTree;
import wickettree.NestedTree;
import wickettree.theme.WindowsTheme;
import wickettree.util.ProviderSubset;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private GanttChart ganttChart;
	
	private CustomerOrderProvider provider = new CustomerOrderProvider();
	private Set<Object> state = new ProviderSubset<Object>(provider);

	public HomePage(final PageParameters parameters) {
		// add gantt chart
		ganttChart = new GanttChart("ganttChart");
		add(ganttChart);

		// add(new Label("version",
		// getApplication().getFrameworkSettings().getVersion()));
		MarkupContainer container = new WebMarkupContainer("container");
		add(container);
		AbstractTree<Object> tree = createTree();
		tree.add(new Behavior() {
			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				response.renderCSSReference(new WindowsTheme());
			}
		});
		container.add(tree);
	}

	protected AbstractTree<Object> createTree() {
		AbstractTree<Object> tree = new NestedTree<Object>("tree", provider, newStateModel()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newContentComponent(String id, IModel<Object> model) {
				Content content = new FolderContent() {

					@Override
					public void onDropFolder(AjaxRequestTarget ajaxRequestTarget) {
						// refresh gantt chart
						ajaxRequestTarget.add(ganttChart);
					}					
				};
				return content.newContentComponent(id, this, model);
			}
		};
		return tree;
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderJavaScriptReference("lib/jquery-1.8.0.min.js");
		response.renderJavaScriptReference("lib/jquery-ui-1.8.23.custom.min.js");
		response.renderJavaScriptReference("lib/date.js");
		response.renderJavaScriptReference("jquery.ganttView.js");
		// css
		response.renderCSSReference("lib/jquery-ui-1.8.23.custom.css");
		response.renderCSSReference("jquery.ganttView.css");
	}

	private IModel<Set<Object>> newStateModel() {
		return new AbstractReadOnlyModel<Set<Object>>() {
			@Override
			public Set<Object> getObject() {
				return state;
			}

			/**
			 * Super class doesn't detach - would be nice though.
			 */
			@Override
			public void detach() {
				((IDetachable) state).detach();
			}
		};
	}
}
