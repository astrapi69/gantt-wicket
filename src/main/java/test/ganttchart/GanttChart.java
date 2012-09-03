package test.ganttchart;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ganttchart.ajax.OnDragGanttAjaxEventBehavior;
import test.ganttchart.ajax.OnDropGanttAjaxEventBehavior;
import test.resource.GanttChartResourceReference;

/**
 * User: Mircea Gaceanu
 */
public class GanttChart extends Panel {
	private static final Logger LOG = LoggerFactory.getLogger(GanttChart.class);

	private JQueryGanttObject jQueryObject;

	/**
	 * @param id
	 */
	public GanttChart(String id) {
		this(id, null);
	}

	/**
	 * jQueryObject.setOption(eventName.najme(),
	 * event.generateStatement(callBackURL));
	 * 
	 * @param id
	 * @param model
	 */
	public GanttChart(String id, JQueryGanttObject jQueryObject) {
		super(id);
		setOutputMarkupId(true);

		this.jQueryObject = jQueryObject;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// init jquery object
		if (jQueryObject == null) {
			jQueryObject = new JQueryGanttObject();
			jQueryObject.setElementId(this.getMarkupId());
			jQueryObject.setOption("dataUrl", "'" + getRequestCycle().urlFor(new GanttChartResourceReference(), null)
					+ "'");
			jQueryObject.setOption("slideWidth", "900");
		}
		// add behavior
		add(new JQueryGanttBehaviour(jQueryObject));
		// add events
		add(new OnDragGanttAjaxEventBehavior(jQueryObject) {
			private static final long serialVersionUID = 406625187849204593L;

			@Override
			protected void onDrag(AjaxRequestTarget target, Date startDate, Date endDate, int scheduleId) {
				LOG.debug("onDrag:: startDate={}, endDate={}, scheduleId={}", new Object[] { startDate, endDate,
						scheduleId });
			}
		});
		add(new OnDropGanttAjaxEventBehavior(jQueryObject) {
			private static final long serialVersionUID = -7387654777626061704L;

			@Override
			protected void onDrop(AjaxRequestTarget target, int coiId) {
				LOG.debug("onDrop:: coiId={}", new Object[] { coiId });
				target.add(GanttChart.this);
			}
		});
	}
}