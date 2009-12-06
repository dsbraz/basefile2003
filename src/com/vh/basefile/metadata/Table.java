/*
 * Created on 12/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.metadata;

import java.util.List;

import com.vh.basefile.exception.OperationNotSuportedException;
import com.vh.basefile.exception.TableException;

/*
 * Author Daniel S. Braz 
 *        dsbraz@yahoo.com.br
 *
 * define uma table
 */
public interface Table extends Metadata {
	
	public String getName();
	
	public void newColumn(String name) throws OperationNotSuportedException;

	public int newRow() throws OperationNotSuportedException;
	
	public int removeRow(int rowid);
	
	public Row getRow(int rowid);
	
	public List getRows();
    
    public List find(String field, String value);
    
    public void delete(String where, String value) throws TableException;
	
	public int length();
	
	public void commitChanges() throws TableException;	

}
