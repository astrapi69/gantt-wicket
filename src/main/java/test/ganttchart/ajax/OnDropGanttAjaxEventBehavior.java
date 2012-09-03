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
public abstract class OnDropGanttAjaxEventBehavior extends GanttAjaxEventBehavior {
    private static final long serialVersionUID = -4416351719287265562L;

    /**
     * Constructor.
     */
    public OnDropGanttAjaxEventBehavior(JQueryObject jQueryObject) {
        super(EventName.onDrop, jQueryObject);
    }

    /**
     * Add here the actions to be done when onDrop is performed.
     * 
     * @param target
     * @param coiId
     */
    protected abstract void onDrop(AjaxRequestTarget target, int coiId);

    @Override
    public void onEvent(AjaxRequestTarget target) {
        IRequestParameters params = RequestCycle.get().getRequest().getQueryParameters();
        int coiId = params.getParameterValue("coiId").toInt();
        onDrop(target, coiId);
    }

    @Override
    protected void encodeAdditionalParams(Map<String, String> params) {
        params.put("coiId", "data");
    }
}
