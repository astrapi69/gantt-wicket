package test.ajax;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;


/**
 * Represents an ajax event of an JQUERY behavior (plugin).
 * 
 * @author Mircea Gaceanu
 */
public abstract class JQueryAjaxEventBehavior extends
		AbstractDefaultAjaxBehavior {

	private EventName eventName;
	private JQueryObject jQueryObject;

	/**
	 * Constructor.
	 * 
	 * @param eventName
	 */
	public JQueryAjaxEventBehavior(EventName eventName,
			JQueryObject jQueryObject) {
		if (eventName == null) {
			throw new IllegalArgumentException(
					"argument [eventName] cannot be null");
		}
		this.eventName = eventName;
		this.jQueryObject = jQueryObject;
	}

	public EventName getEventName() {
		return eventName;
	}

	@Override
	protected void onBind() {
		super.onBind();

		jQueryObject.setOption(eventName.name(), getEventHandler());
	}

	/**
	 * Listener method for the ajax event
	 * 
	 * @param target
	 */
	protected abstract void onEvent(final AjaxRequestTarget target);

	@Override
	protected void respond(AjaxRequestTarget target) {
		onEvent(target);
	}

	/**
	 * 
	 * @return event handler
	 */
	protected CharSequence getEventHandler() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFunctionSignature());

		// append callback script
		sb.append(getCallbackScript());

		sb.append(getFunctionEnd());
		return sb.toString();
	}

	protected String getFunctionSignature() {
		return "function() {\n";
	}

	protected String getFunctionEnd() {
		return "\n }";
	}
}