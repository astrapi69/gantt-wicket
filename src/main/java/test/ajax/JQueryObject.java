package test.ajax;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to keep a jQuery statement properties, e.g. method name, options,
 * etc
 * 
 * @author Mircea Gaceanu
 */
public class JQueryObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory
			.getLogger(JQueryObject.class);

	/**
	 * the jQuery method
	 */
	private String methodName;
	/**
	 * the html element id
	 */
	private String elementId;
	/**
	 * the jQuery url
	 */
	private String ajaxUrl;
	/**
	 * the map containing the jQuery method options
	 */
	private Map<String, Object> options = new HashMap<String, Object>();
	/**
	 * the map containing the jQuery method classes
	 */
	private Map<String, String> classes = new HashMap<String, String>();

	public JQueryObject() {
	}

	public JQueryObject(String methodName) {
		// set default jQuery method name
		this.setMethodName(methodName);
	}

	public JQueryObject(String methodName, Map<String, String> classes) {
		this(methodName);

		// set jQuery default classes
		this.classes.putAll(classes);
	}

	public Object getOption(String key) {
		return options.get(key);
	}

	public void setOption(String key, Object value) {
		options.put(key, value);
	}

	public void removeOption(String key) {
		options.remove(key);
	}

	public String getClass(String key) {
		return classes.get(key);
	}

	public void addClass(String key, String value) {
		classes.put(key, value);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getAjaxUrl() {
		return ajaxUrl;
	}

	public void setAjaxUrl(String ajaxUrl) {
		this.ajaxUrl = ajaxUrl;
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

	public Map<String, String> getClasses() {
		return classes;
	}

	public void setClasses(Map<String, String> classes) {
		this.classes = classes;
	}

	/**
	 * Build the jQuery statement from JQueryObject object.
	 * 
	 * @return the string containing the jQuery code statement for the
	 *         JQueryObject element
	 */
	public String generateJQueryStatement(Component component) {
		StringBuffer sb = new StringBuffer();
		sb.append("$(document).ready(function(){");
		sb.append(generateJQueryStatementBody());
		sb.append("});");

		LOG.debug(sb.toString());
		return sb.toString();
	}

	/**
	 * Used only for slider redraw
	 * 
	 * @return JS to be executed for slider redraw
	 */
	public String generateJQueryBody() {
		// called generateJQueryStatementBody because the code was identical
		return generateJQueryStatementBody();
	}

	/**
	 * Builds the jQuery statement body.
	 * 
	 * @return the jQuery statement body
	 */
	protected String generateJQueryStatementBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("$(\"#");
		sb.append(getElementId());
		sb.append("\").");
		sb.append(getMethodName());
		sb.append("(");
		if (getAjaxUrl() != null && !getAjaxUrl().isEmpty()) {
			sb.append("\"");
			sb.append(getAjaxUrl());
			sb.append("\", ");
		}
		sb.append("{\n"); // options bracket start
		sb.append(getJsMap(getOptions(), false));
		if (getClasses() != null && !getClasses().isEmpty()) {
			sb.append(", classes: ");
			sb.append(getJsMap(getClasses(), true));
		}
		sb.append("\n}"); // options bracket end
		sb.append(");\n");
		return sb.toString();
	}

	/**
	 * Builds the jQuery options statement to be added to the body.
	 * 
	 * @return the jQuery options statement to be added to the body.
	 */
	public Object getJsMap(Map<String, ?> map, boolean addBrackets) {
		if (map == null || map.isEmpty()) {
			return "";
		}
		String sep = " ";
		StringBuffer strb = new StringBuffer();
		if (addBrackets) {
			strb.append("{");
		}
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			strb.append(sep);
			strb.append(entry.getKey()).append(":").append(entry.getValue());
			sep = ", \n ";
		}
		if (addBrackets) {
			strb.append("}\n");
		}
		return strb.toString();
	}
}
