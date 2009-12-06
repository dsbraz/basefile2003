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
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.vh.basefile.core.Engine;
import com.vh.basefile.exception.DatasetException;
import com.vh.basefile.exception.OperationNotSuportedException;
import com.vh.basefile.exception.TableException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa uma tabela
 */
public class TableImpl implements Table {
	
	private SortedMap _defs;
	private List rows;	
	private String name;

	TableImpl(String tbName) {
		_defs = new TreeMap();
		rows = new ArrayList();
		this.name = tbName;
	}

	public String getName() {
		return name;
	}

	public List getDefinitions() {
        return new ArrayList(this._defs.keySet());
	}

	public void newColumn(String name) throws OperationNotSuportedException {
		if (!rows.isEmpty()) {
			throw new OperationNotSuportedException("Não é possível alterar a " +
			                                        "definição da tabela");
		}
	    _defs.put(name, "null");
	}

	public int newRow() throws OperationNotSuportedException {
		if (_defs.isEmpty()) {
			throw new OperationNotSuportedException("Não existe definição para " +
			                                        "esta tabela");
		}
		Row row = new RowImpl(_defs);				
		rows.add(row);
		return rows.indexOf(row);
	}

	public int removeRow(int rowid) {
		rows.remove(rowid);
		return rowid;
	}

	public Row getRow(int rowid) {
		return (Row)rows.get(rowid); 
	}

	public List getRows() {
		return rows;
	}

    public List find(String field, String value) {
        List result = new ArrayList();
        Iterator it = rows.iterator();
        while (it.hasNext()) {
            Row row = (Row)it.next();
            if (row.get(field).equals(value)) {
                result.add(row);
            }
        }
        return result;
    }

    public void delete(String where, String value) throws TableException {
        List toRemove = new ArrayList();
        Iterator it = this.rows.iterator();
        while (it.hasNext()) {
            Row row = (Row)it.next();
            String s = (String)row.get(where);
            if (s.equals(value)) {
                toRemove.add(row);
            }
        }
        this.rows.removeAll(toRemove);
    }

	public int length() {
		return rows.size();
	}

	public void commitChanges() throws TableException {
		try {
		    Engine.getInstance().update(this);
		}
		catch (DatasetException e) {
			throw new TableException(e.getMessage());
		}
	}

}
