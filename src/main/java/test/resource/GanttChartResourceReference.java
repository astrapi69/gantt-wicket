package test.resource;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * @author Mircea Gaceanu
 */
public class GanttChartResourceReference extends ResourceReference {

	/**
	 */
	public GanttChartResourceReference() {
		super("ganttChart");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.request.resource.ResourceReference#getResource()
	 */
	@Override
	public IResource getResource() {
		return new GanttChartResource();
	}

}
