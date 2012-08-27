package test.ganttchart.ajax;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import test.ajax.EventName;
import test.ajax.JQueryAjaxEventBehavior;
import test.ajax.JQueryObject;

/**
 * @author Mircea Gaceanu
 */
public abstract class GanttAjaxEventBehavior extends JQueryAjaxEventBehavior {

	/**
	 * @param eventName
	 */
	public GanttAjaxEventBehavior(EventName eventName, JQueryObject jQueryObject) {
		super(eventName, jQueryObject);
	}

	/**
	 * @param params
	 */
	protected abstract void encodeAdditionalParams(Map<String, String> params);

	@Override
	protected String getFunctionSignature() {
		return "function (data) {\n";
	}

	@Override
	public CharSequence getCallbackUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getCallbackUrl());
		Map<String, String> params = new HashMap<String, String>();
		encodeAdditionalParams(params);
		for (Entry<String, String> entry : params.entrySet()) {
			sb.append("&").append(entry.getKey()).append("=' + ")
					.append(entry.getValue()).append("+'");
		}

		return sb.toString();
	}
}
