package test.ganttchart.ajax;

import java.util.Date;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;

import test.ajax.EventName;
import test.ajax.JQueryObject;

/**
 * @author Mircea Gaceanu
 */
public abstract class OnDragGanttAjaxEventBehavior extends
		GanttAjaxEventBehavior {
	private static final long serialVersionUID = -4416351719287265562L;

	/**
	 * Constructor.
	 */
	public OnDragGanttAjaxEventBehavior(JQueryObject jQueryObject) {
		super(EventName.onDrag, jQueryObject);
	}

	/**
	 * Add here the actions to be done when onDrag is performed.
	 * 
	 * @param target
	 * @param startDate
	 * @param endDate
	 * @param scheduleId
	 */
	protected abstract void onDrag(AjaxRequestTarget target, Date startDate,
			Date endDate, int scheduleId);

	@Override
	public void onEvent(AjaxRequestTarget target) {
		IRequestParameters params = RequestCycle.get().getRequest()
				.getQueryParameters();
		Date startDate = new Date(params.getParameterValue("startDate")
				.toLong());
		Date endDate = new Date(params.getParameterValue("endDate").toLong());
		int scheduleId = params.getParameterValue("scheduleId").toInt();
		onDrag(target, startDate, endDate, scheduleId);
	}

	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("startDate", "data.start.getTime()");
		params.put("endDate", "data.end.getTime()");
		params.put("scheduleId", "data.id");
	}
}
