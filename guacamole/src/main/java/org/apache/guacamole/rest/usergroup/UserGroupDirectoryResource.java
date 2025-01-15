/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.rest.usergroup;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.AuthenticatedUser;
import org.apache.guacamole.net.auth.UserGroup;
import org.apache.guacamole.net.auth.Directory;
import org.apache.guacamole.net.auth.Permissions;
import org.apache.guacamole.net.auth.UserContext;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;
import org.apache.guacamole.rest.directory.DirectoryObjectResourceFactory;
import org.apache.guacamole.rest.directory.DirectoryObjectTranslator;
import org.apache.guacamole.rest.directory.DirectoryResource;

/**
 * A REST resource which abstracts the operations available on a Directory of
 * UserGroups.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserGroupDirectoryResource extends DirectoryResource<UserGroup, APIUserGroup> {

    /**
     * Creates a new UserGroupDirectoryResource which exposes the operations
     * and subresources available for the given UserGroup Directory.
     *
     * @param authenticatedUser
     *     The user that is accessing this resource.
     *
     * @param userContext
     *     The UserContext associated with the given Directory.
     *
     * @param directory
     *     The Directory being exposed.
     *
     * @param translator
     *     A DirectoryObjectTranslator implementation which handles
     *     UserGroups.
     *
     * @param resourceFactory
     *     A factory which can be used to create instances of resources
     *     representing UserGroups.
     */
    @AssistedInject
    public UserGroupDirectoryResource(
            @Assisted AuthenticatedUser authenticatedUser,
            @Assisted UserContext userContext,
            @Assisted Directory<UserGroup> directory,
            DirectoryObjectTranslator<UserGroup, APIUserGroup> translator,
            DirectoryObjectResourceFactory<UserGroup, APIUserGroup> resourceFactory) {
        super(authenticatedUser, userContext, UserGroup.class, directory, translator, resourceFactory);
    }

    @Override
    protected ObjectPermissionSet getObjectPermissions(Permissions permissions)
            throws GuacamoleException {
        return permissions.getUserGroupPermissions();
    }

}
