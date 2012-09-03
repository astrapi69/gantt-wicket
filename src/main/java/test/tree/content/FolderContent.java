/*
 * Copyright 2009 Sven Meier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.tree.content;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior;
import org.odlabs.wiquery.ui.draggable.DraggableRevert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ganttchart.GanttChart;
import wickettree.AbstractTree;
import wickettree.content.Folder;
import de.brueckner.mms.businessdefact.data.CustomerOrderItem;

/**
 * @param <T>
 */
public abstract class FolderContent<T> extends Content<T> {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(GanttChart.class);

	/**
	 * Add custom code to be run on valid folder dropping.
	 * 
	 * @param ajaxRequestTarget
	 */
	public abstract void onDropFolder(AjaxRequestTarget ajaxRequestTarget);

	@Override
	public Component newContentComponent(String id, final AbstractTree<T> tree, IModel<T> model) {
		Folder<T> folder = new Folder<T>(id, tree, model) {
			@Override
			protected IModel<?> newLabelModel(IModel<T> tiModel) {
				return new PropertyModel<String>(tiModel, "key");
			}

			@Override
			protected void onBeforeRender() {
				if (isEnabledInHierarchy() && !isClickable()) {
					setMarkupId("" + ((CustomerOrderItem) getDefaultModelObject()).getId());

					// add draggable behavior to label
					DraggableBehavior draggableBehavior = new DraggableBehavior();
					// DraggableAjaxBehavior draggableBehavior = new
					// DraggableAjaxBehavior(DraggableEvent.STOP) {
					//
					// @Override
					// public void onDrag(Component draggedComponent,
					// AjaxRequestTarget ajaxRequestTarget) {
					// // TODO Auto-generated method stub
					//
					// }
					//
					// @Override
					// public void onStart(Component draggedComponent,
					// AjaxRequestTarget ajaxRequestTarget) {
					// // TODO Auto-generated method stub
					//
					// }
					//
					// @Override
					// public void onStop(Component draggedComponent,
					// AjaxRequestTarget ajaxRequestTarget) {
					// // TODO Auto-generated method stub
					//
					// }
					//
					// public void onValid(Component draggedComponent,
					// AjaxRequestTarget ajaxRequestTarget) {
					// LOG.debug("onValid:: draggedComponent={}",
					// draggedComponent);
					// // add custom code to be run on valid folder
					// // dropping
					// onDropFolder(ajaxRequestTarget);
					// }
					// };
					draggableBehavior.setScope("chart");
					draggableBehavior.setRevert(new DraggableRevert(Boolean.TRUE));
					this.add(draggableBehavior);
				}
				// call super
				super.onBeforeRender();
			}
		};
		return folder;
	}
}