/*
 * Created on 09/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vh.basefile.core.Engine;
import com.vh.basefile.exception.DatabaseException;
import com.vh.basefile.exception.DatasetException;
import com.vh.basefile.exception.TableNotFoundException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa um banco de dados
 */
public class DatabaseImpl implements Database {
	
	private Map openTables;
	private List _securityList;
	private String name;
	
	public DatabaseImpl(String dbName) {
		openTables = new HashMap();
		_securityList = new ArrayList();
		this.name = dbName;		
	}
	
	public DatabaseImpl(String dbName, List securityList) {
		this(dbName);
		_securityList.addAll(securityList);		
	}
	
	public String getName() {
		return this.name;
	}
	
	public List getSecurityList() {
		return this._securityList;
	}
	
	public Table newTable(String tbName) {
		Table tb = new TableImpl(tbName);
		_securityList.add(tbName);
		openTables.put(tbName, tb);
		return tb;
	}
	
	public void dropTable(String tbName) {
		openTables.remove(tbName);
		_securityList.remove(tbName);
	}
	
	public Table openTable(String tbName) throws TableNotFoundException {
		if (!_securityList.contains(tbName)) {
			throw new TableNotFoundException("Tabela não foi encontrada");
		}
        if (!openTables.containsKey(tbName)) {
            Table tb = Engine.getInstance().loadTable(this, tbName);
            openTables.put(tbName, tb);
        }
		return (Table)openTables.get(tbName);
	}

	public void closeTable(String tbName) throws TableNotFoundException {
		openTables.remove(tbName);
	}

	public List getOpenTables() {
		return new ArrayList(openTables.values());
	}

	public void commitChanges() throws DatabaseException {
		try {
		    Engine.getInstance().update(this);
		}
		catch (DatasetException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
