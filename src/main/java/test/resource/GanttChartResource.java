package test.resource;

import org.apache.wicket.request.resource.AbstractResource;

/**
 * @author Mircea Gaceanu
 */
public class GanttChartResource extends AbstractResource {
	private static final long serialVersionUID = -4277094607082534142L;

	/**
	 * 
	 */
	public GanttChartResource() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.request.resource.AbstractResource#newResourceResponse
	 * (org.apache.wicket.request.resource.IResource.Attributes)
	 */
	@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		ResourceResponse response = new ResourceResponse();
		response.setContentType("application/json");
		response.setWriteCallback(new WriteCallback() {

			@Override
			public void writeData(Attributes attributes) {
				attributes
						.getResponse()
						.write("[{\"id\": \"1\", "
								+ "\"name\": \"Feature 2\", "
								+ "\"series\": "
								+ "["
								+ "{ \"name\": \"Planned\", "
								+ "\"start\": \"January 01, 2010\", "
								+ "\"end\": \"January 03, 2010\" },"
								+ "{ \"name\": \"Actual\", "
								+ "\"start\": \"January 02, 2010\", "
								+ "\"end\": \"January 05, 2010\", "
								+ "\"color\": \"#f0f0f0\" }"
								+ "]"
								+ "}]");
			}
		});
		return response;
	}
}
