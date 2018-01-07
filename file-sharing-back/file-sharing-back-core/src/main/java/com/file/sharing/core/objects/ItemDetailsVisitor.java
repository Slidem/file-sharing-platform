package com.file.sharing.core.objects;

import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileDetails;

/**
 * @author Alexandru Mihai
 * @created Jan 6, 2018
 * 
 */
public interface ItemDetailsVisitor {

	public void visit(FileDetails fileDetails);

	public void visit(DirectoryDetails directory);

}
