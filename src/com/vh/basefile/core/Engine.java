/*
 * Created on 10/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vh.basefile.dataset.Dataset;
import com.vh.basefile.dataset.DatasetHandler;
import com.vh.basefile.dataset.DatasetReader;
import com.vh.basefile.dataset.DatasetWriter;
import com.vh.basefile.exception.DatabaseNotFoundException;
import com.vh.basefile.exception.DatasetException;
import com.vh.basefile.exception.TableNotFoundException;
import com.vh.basefile.metadata.Database;
import com.vh.basefile.metadata.DatabaseImpl;
import com.vh.basefile.metadata.Row;
import com.vh.basefile.metadata.RowImpl;
import com.vh.basefile.metadata.Table;
import com.vh.basefile.metadata.TableImpl;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa o engine de persistencia
 * 
 */
public class Engine {
	
	private static Engine instance;
	
	private Engine() {
		//
	}
	
	public static Engine getInstance() {
		if (instance == null) {
			instance = new Engine();
		}
		return instance;
	}
	
	public Database loadDatabase(String dbName) throws DatabaseNotFoundException {
		try {
		    Dataset ds = _getDataset(dbName);
		    List sl = DatasetReader.readSecurityList(ds);
		    Database db = new DatabaseImpl(dbName, sl);
		    _closeDataset(ds);
		    return db;
		}
		catch (DatasetException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}
	
	public Table loadTable(Database db, String tbName) throws TableNotFoundException {
		try {
			Dataset ds = _getDataset(tbName);
			Table tb = db.newTable(tbName);

			List cols = DatasetReader.readTableDefs(ds);

			Iterator it_col = cols.iterator();
			while (it_col.hasNext()) {
                String colName = (String)it_col.next();
				tb.newColumn(colName);
			}

			List data = DatasetReader.readTableData(ds);

			Iterator it_row0 = data.iterator();
			while (it_row0.hasNext()) {
				Iterator it_row1 = cols.iterator();
				Row row = tb.getRow(tb.newRow());
				while (it_row1.hasNext()) {
				    row.update((String)it_row1.next(), it_row0.next());
				}
			}

            _closeDataset(ds);				

			return tb;
		}
		catch (Exception e) {
			throw new TableNotFoundException(e.getMessage());
		}
	}
	
	public void update(Database db) throws DatasetException {
		DatabaseImpl dbi = (DatabaseImpl)db;
		Dataset ds = _getDataset(dbi.getName());
		try {
            DatasetWriter.writeDatabaseFileHeader(ds);
		    DatasetWriter.writeDatabaseName(ds, dbi.getName());
		    DatasetWriter.writeSecurityList(ds, dbi.getSecurityList());
            DatasetWriter.writeDatabaseFileFooter(ds);
		    _saveDataset(ds);
		}
		finally {
		    _closeDataset(ds);
		}
	}

	public void update(Table tb) throws DatasetException {
		TableImpl tbi = (TableImpl)tb;
		Dataset ds = _getDataset(tbi.getName());
		try {            
            DatasetWriter.writeTableFileHeader(ds);
		    DatasetWriter.writeTableName(ds, tbi.getName());
		    DatasetWriter.writeTableDefs(ds, tbi.getDefinitions());
            ArrayList data = new ArrayList();
		    List rows = tbi.getRows();
		    Iterator it = rows.iterator();
		    while (it.hasNext()) {
			    RowImpl row = (RowImpl)it.next();
                data.addAll(row.getValues());
		    }
            DatasetWriter.writeTableData(ds, data);
            DatasetWriter.writeTableFileFooter(ds);
		    _saveDataset(ds);
		}
		finally {
		    _closeDataset(ds);
		}		
	}

	private Dataset _getDataset(String dsName) throws DatasetException {
		return DatasetHandler.getDataset(dsName);
	}

	private void _closeDataset(Dataset ds) throws DatasetException {
		DatasetHandler.closeDataset(ds);
	}

	private void _saveDataset(Dataset ds) throws DatasetException {
		DatasetHandler.saveDataset(ds);
	}	

}
