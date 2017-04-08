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
package org.jkiss.dbeaver.ui.actions.navigator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.jkiss.dbeaver.ui.navigator.database.NavigatorViewBase;

import java.util.Iterator;

public class NavigatorHandlerExpandAll extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
        if (activePart instanceof NavigatorViewBase) {
            TreeViewer navigatorViewer = ((NavigatorViewBase) activePart).getNavigatorViewer();
            ISelection selection = navigatorViewer.getSelection();
            if (selection.isEmpty()) {
                navigatorViewer.expandAll();
            } else if (selection instanceof IStructuredSelection) {
                for (Iterator iter = ((IStructuredSelection) selection).iterator(); iter.hasNext(); ) {
                    navigatorViewer.expandToLevel(iter.next(), TreeViewer.ALL_LEVELS);
                }
            }
        }
        return null;
    }
}