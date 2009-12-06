/*
 * Created on 12/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.connection;

import java.util.List;

import com.vh.basefile.exception.DatabaseNotFoundException;
import com.vh.basefile.metadata.Database;

/*
 * Author Daniel S. Braz 
 *        dsbraz@yahoo.com.br
 *
 * define uma conexão
 */
public interface Connection {
	
	public Database openDatabase(String dbName) throws DatabaseNotFoundException;
	
	public List getOpenDatabases();
	
	public void closeDatabase(Database db);

}
