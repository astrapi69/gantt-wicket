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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import wickettree.AbstractTree;
import wickettree.content.Folder;

/**
 * @param <T>
 */
public class FolderContent<T> extends Content<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public Component newContentComponent(String id, final AbstractTree<T> tree, IModel<T> model) {
        return new Folder<T>(id, tree, model) {
            @Override
            protected IModel<?> newLabelModel(IModel<T> tiModel) {
                return new PropertyModel<String>(tiModel, "key");
            }
        };
    }
}