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

package org.jkiss.dbeaver.ui;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;

/**
 * IDataSourceConnectionEditorSite
 */
public interface IDataSourceConnectionEditorSite
{
    DBRRunnableContext getRunnableContext();

    DBPDataSourceRegistry getDataSourceRegistry();

    boolean isNew();

    DBPDriver getDriver();

    @NotNull
    DBPDataSourceContainer getActiveDataSource();

    void updateButtons();

    boolean openDriverEditor();

    boolean openSettingsPage(String pageId);

}
