/*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Julien Guyon						e-mail: Julien.guyon@loria.fr
 * 
 **/
package fr.loria.eclipse.jtom.compilation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

import fr.loria.eclipse.gom.refactoring.GomDeleteParticipant;

/**
 * This class is an extension to the default compilator , it is used when a
 * project/clean is performed to delete the files generated by gom
 * 
 * @author Martin GRANDCOLAS
 * 
 */
public class TomCompilationParticipant extends CompilationParticipant implements
		IResourceVisitor {

	/*
	 * This boolean is used because the cleanStarting method is called two times
	 */

	private GomDeleteParticipant part;

	private boolean performed = false;

	public TomCompilationParticipant() {
		this.part = new GomDeleteParticipant();

	}

	public void cleanStarting(IJavaProject project) {
		//System.out.println("cleanStarting ");
		if (!performed) {
			try {

				IProject res = (IProject) project.getCorrespondingResource();
				res.accept(this);

			} catch (JavaModelException e) {
				System.out.println("Error when cleaning the project");

			} catch (CoreException e) {
				System.out
						.println("Error when cleaning the project : cannot add a ResourceVisitor");
			}
		}
		performed = !performed;
	}

	public void buildStarting(BuildContext[] files, boolean isBatch) {
		//System.out.println("build starting");		
	}

	public int aboutToBuild(IJavaProject project) {
		//System.out.println("about To Build");

		return super.aboutToBuild(project);
	}

	public boolean isActive(IJavaProject project) {
		//System.out.println("isActive");

		//TODO know the project nature to know if this extension should be enabled or not
		return true;
	}

	public boolean visit(IResource resource) throws CoreException {
		switch (resource.getType()) {
		case IResource.FILE:
			part.delete((IFile) resource);
			break;

		default:
			break;
		}
		return true;
	}

}