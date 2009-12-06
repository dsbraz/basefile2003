/*
 * Created on 09/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vh.basefile.core.Engine;
import com.vh.basefile.exception.DatabaseNotFoundException;
import com.vh.basefile.exception.InvalidUserException;
import com.vh.basefile.metadata.Database;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa uma conexão
 * 
 */
public class ConnectionImpl implements Connection {
	
	private Map databases;
	
	public ConnectionImpl(String userName) throws InvalidUserException {
		if (userName == null || userName.equals("")) {
			throw new InvalidUserException("Usuário inválido");
		}
		this.databases = new HashMap();
	}
	
	public Database openDatabase(String dbName) throws DatabaseNotFoundException {
        if (!this.databases.containsKey(dbName)) {
		    Database db = Engine.getInstance().loadDatabase(dbName);
		    this.databases.put(dbName, db);
        }
		return (Database)this.databases.get(dbName);
	}
	
	public List getOpenDatabases() {
		return new ArrayList(this.databases.values());
	}
	
	public void closeDatabase(Database db) {
		this.databases.remove(db.getName());
	}

}
