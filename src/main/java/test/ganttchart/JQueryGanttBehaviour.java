package test.ganttchart;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * User: Mircea Gaceanu
 */
public class JQueryGanttBehaviour extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 3378699023618634983L;

	/** The jQuery object. */
	private JQueryGanttObject jQueryObject;

	public JQueryGanttBehaviour(JQueryGanttObject jQueryObject) {
		this.jQueryObject = jQueryObject;
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		// add javascript and css resources
		response.renderOnDomReadyJavaScript(jQueryObject
				.generateJQueryStatement(component));
	}

	@Override
	protected void respond(AjaxRequestTarget target) {
		// not needed
	}
}