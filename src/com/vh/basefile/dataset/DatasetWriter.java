/*
 * Created on 11/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.dataset;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.vh.basefile.exception.WriteDatasetException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa uma classe helper
 * para escrever no dataset 
 */
public class DatasetWriter implements DatasetOperators {
	
	/*
	 * [FILE]
	 *   [DATABASE]
	 *     NAME=DB_TESTE\&eod
	 *     SECURITY_LIST=TB_ACTION,TB_DOMAIN,TB_USERS\&eod
	 *   [\DATABASE]
	 * [\FILE]
	 *
	 * [FILE]
	 *   [TABLE]
	 *     NAME=TB_TESTE\&eod
	 *     DEF=COLUMN_1,COLUMN_2,COLUMN_3\&eod
	 *     DATA=VALUE11,VALUE12,VALUE13, 
	 *          VALUE21,VALUE22,VALU23,
	 *          VALUE31,VALUE32,VALU33\&eod
	 *   [\TABLE]
	 * [\FILE]
	 */
     
    public static void clearDataset(Dataset ds) throws WriteDatasetException {
        try {
            ds.setLength(0);
        }
        catch (IOException e) {
            throw new WriteDatasetException(e.getMessage());
        }
    }
     
    public static void writeTableFileHeader(Dataset ds) 
      throws WriteDatasetException {
        clearDataset(ds);
        write(ds, BOF + CRLF);
        write(ds, BOT + CRLF);
    }
    
    public static void writeTableFileFooter(Dataset ds) 
      throws WriteDatasetException {
        write(ds, EOT + CRLF);
        write(ds, EOF);
    }
    
    public static void writeDatabaseFileHeader(Dataset ds) 
      throws WriteDatasetException {
        write(ds, BOF + CRLF);
        write(ds, BOB + CRLF);
    }
    
    public static void writeDatabaseFileFooter(Dataset ds) 
      throws WriteDatasetException {
        write(ds, EOB + CRLF);
        write(ds, EOF);
    }    
	
	public static void writeDatabaseName(Dataset ds, String dbName)
	  throws WriteDatasetException {
        write(ds, NAME + AOP + dbName + CRLF);
	}
	
	public static void writeSecurityList(Dataset ds, List sl) 
	  throws WriteDatasetException {
        Iterator it = sl.iterator();
        String can = SELI + AOP;
        while (it.hasNext()) {
            can += String.valueOf(it.next()) + SOP;
        }
        if (can.indexOf(SOP) > -1) {
            can = can.substring(0, can.lastIndexOf(SOP));
        }
        write(ds, can + EOD + CRLF);
	}
	
	public static void writeTableName(Dataset ds, String tbName) 
	  throws WriteDatasetException {
        write(ds, NAME + AOP + tbName + EOD + CRLF);
	}	
	
	public static void writeTableDefs(Dataset ds, List def) 
	  throws WriteDatasetException {
        Iterator it = def.iterator();
        String can = DEFS + AOP;
        while (it.hasNext()) {
            can += String.valueOf(it.next()) + SOP;
        }
        if (can.indexOf(SOP) > -1) {
            can = can.substring(0, can.lastIndexOf(SOP));
        }
        write(ds, can + EOD + CRLF);
    }

	public static void writeTableData(Dataset ds, List dt) 
	  throws WriteDatasetException {
        Iterator it = dt.iterator();
        String can = DATA + AOP;
        while (it.hasNext()) {
            can += String.valueOf(it.next()) + SOP;
        }
        if (can.indexOf(SOP) > -1) {
            can = can.substring(0, can.lastIndexOf(SOP));
        }
        write(ds, can + EOD + CRLF);          
	}

    private static synchronized void write(Dataset ds, String value) 
      throws WriteDatasetException {
        try {
            if (value == null || value.trim().equals("")) { value = NULL; }
            ds.seek(ds.length());
            ds.write(value.getBytes());
            
            // DEBUG -- DatasetReader:Write
            System.out.println("DatasetWriter:Write:" + value);            
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new WriteDatasetException("Arquivo corrompido. Não foi " + 
                                            "possível efetuar a gravação"); 
        }
    }

}
