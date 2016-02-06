package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

/**
 * Classes are expected to implement this interface to receive notifications
 * about job completions.  To be informed about the status of a particular
 * job, CyJobHandlers should be registered as services so that jobs can be
 * reassociated with their handlers on session restore.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJobHandler {
	public void handleJob(CyJob job, CyJobStatus status);
}