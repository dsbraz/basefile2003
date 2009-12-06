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
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa uma linha de uma tabela
 * 
 */
public class RowImpl implements Row {
	
	private SortedMap columns;
	private int stupdate;
	
	RowImpl(Map defs) {
		columns = new TreeMap(defs);
		stupdate = 0;
	}
	
	public List getValues() {
		return new ArrayList(columns.values());
	}
	
	public int status() {
		return stupdate;
	}
	
	public Object get(String column) {
		return columns.get(column);
	}
		
	public void update(String column, Object value) {
		stupdate = 1;
		columns.put(column, value);
	}

}
