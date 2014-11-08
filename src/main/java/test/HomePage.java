package test;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import test.ganttchart.GanttChart;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private GanttChart ganttChart;
	
	public HomePage(final PageParameters parameters) {
		// add gantt chart
		ganttChart = new GanttChart("ganttChart");
		add(ganttChart);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		// js
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(HomePage.class, "jquery-1.8.0.min.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(HomePage.class, "jquery-ui-1.8.23.custom.min.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(HomePage.class, "date.js")));
		response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(HomePage.class, "jquery.ganttView.js")));
		// css
		response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "jquery-ui-1.8.23.custom.css")));
		response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "jquery.ganttView.css")));		
	}

}
