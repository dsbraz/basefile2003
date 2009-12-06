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

import com.vh.basefile.exception.DatabaseException;
import com.vh.basefile.exception.TableNotFoundException;

/*
 * Author Daniel S. Braz 
 *        dsbraz@yahoo.com.br
 *
 * define um database
 */
public interface Database extends Metadata {
	
	public String getName();
	
	public Table newTable(String tbName);
	
	public void dropTable(String tbName);
	
	public Table openTable(String tbName) throws TableNotFoundException;
	
	public void closeTable(String tbName) throws TableNotFoundException;
	
	public List getOpenTables();
	
	public void commitChanges() throws DatabaseException;	

}
