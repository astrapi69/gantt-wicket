package test;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import test.ganttchart.GanttChart;
import test.service.CustomerService;
import test.tree.content.Content;
import test.tree.content.FolderContent;
import test.tree.provider.CustomerOrderProvider;
import wickettree.AbstractTree;
import wickettree.NestedTree;
import wickettree.theme.WindowsTheme;
import wickettree.util.ProviderSubset;

import java.util.Set;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        // add gantt chart
        add(new GanttChart("ganttChart"));

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
        CustomerOrderProvider coProvider = new CustomerOrderProvider();
        AbstractTree<Object> tree = new NestedTree<Object>("tree", coProvider, newStateModel(coProvider)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected Component newContentComponent(String id, IModel<Object> model) {
                Content content = new FolderContent();
                return content.newContentComponent(id, this, model);
            }
        };
        CustomerService customerService = new CustomerService();
        return tree;
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

    private IModel<Set<Object>> newStateModel(final CustomerOrderProvider provider) {
        return new AbstractReadOnlyModel<Set<Object>>() {
            @Override
            public Set<Object> getObject() {
//                return new InverseSet<Object>(new ProviderSubset<Object>(provider));
                return   new ProviderSubset<Object>(provider);
            }

            /**
             * Super class doesn't detach - would be nice though.
             */
            @Override
            public void detach() {
                ((IDetachable) getObject()).detach();
            }
        };
    }
}
