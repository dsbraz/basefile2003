/*
 * Created on 11/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.vh.basefile.exception.ReadDatasetException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa classe helper para 
 * ler o dataset  
 */
public class DatasetReader implements DatasetOperators{
	
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

	public static String readDatabaseName(Dataset ds) 
	  throws ReadDatasetException {
		return read(ds, NAME);
	}

	public static List readSecurityList(Dataset ds) 
	  throws ReadDatasetException {
	  	String s = read(ds, SELI);
	  	StringTokenizer st = new StringTokenizer(s, SOP, false);	  	
        List l = new ArrayList();
        while (st.hasMoreElements()) {
        	l.add(st.nextElement());
        }
		return l;
	}

	public static String readTableName(Dataset ds) 
	  throws ReadDatasetException {
		return read(ds, NAME);
	}	

	public static List readTableDefs(Dataset ds) 
	  throws ReadDatasetException {
		String s = read(ds, DEFS);
		StringTokenizer st = new StringTokenizer(s, SOP, false);	  	
		List ls = new ArrayList();
		while (st.hasMoreElements()) {
			ls.add(st.nextElement());
		}
		return ls;
	}

	public static List readTableData(Dataset ds) 
	  throws ReadDatasetException {
		String s = read(ds, DATA);
		StringTokenizer st = new StringTokenizer(s, SOP, false);
		List ls = new ArrayList();
		while (st.hasMoreElements()) {
			ls.add(st.nextElement());
		}
		return ls;
	}

	private static synchronized String read(Dataset ds, 
	  String key) throws ReadDatasetException {
	  	try {
            ds.seek(0);
            String result;
			String line = ds.readLine();            
            if (!line.startsWith(BOF)) {
                throw new Exception();
            }            
			while (!line.startsWith(EOF) && !line.startsWith(key)) {
                  line = ds.readLine().trim();
			}
			while (!line.startsWith(EOF) && !line.endsWith(EOD)) {
                  line += ds.readLine().trim();
			}
            result = line.substring(line.indexOf(AOP) + 1, line.indexOf(EOD));

            // DEBUG -- DatasetReader:Read
            System.out.println("DatasetReader:Read:" + result);
            
			return result;
	  	}
	  	catch (Exception e) {
            e.printStackTrace();
            throw new ReadDatasetException("Arquivo corrompido. Não foi " + 
                                           "possível efetuar a leitura");	
	  	}
	}

}
