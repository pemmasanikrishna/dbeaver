/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2017 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.core.DBeaverCore;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.navigator.DBNNode;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.ui.editors.entity.NodeEditorInput;

public class NodeEditorInputFactory implements IElementFactory
{
    private static final Log log = Log.getLog(NodeEditorInputFactory.class);

    public static final String ID_FACTORY = NodeEditorInputFactory.class.getName(); //$NON-NLS-1$

    private static final String TAG_NODE = "node"; //$NON-NLS-1$


    public NodeEditorInputFactory()
    {
    }

    @Override
    public IAdaptable createElement(IMemento memento)
    {
        // Get the node path.
        final String nodePath = memento.getString(TAG_NODE);
        if (nodePath == null) {
            return null;
        }
        final DBNModel navigatorModel = DBeaverCore.getInstance().getNavigatorModel();

        try {
            final DBNNode node = navigatorModel.getNodeByPath(VoidProgressMonitor.INSTANCE, nodePath);
            if (node != null) {
                return new NodeEditorInput(node);
            }
        } catch (DBException e) {
            log.error("Error opening node '" + nodePath + "'", e);
            return null;
        }
        return null;
    }

    public static void saveState(IMemento memento, NodeEditorInput input)
    {
        final DBNNode node = input.getNavigatorNode();
        memento.putString(TAG_NODE, node.getNodeItemPath());
    }

}