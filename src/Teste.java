/*
 * Created on 12/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
 
import java.util.Iterator;
import java.util.List;

import com.vh.basefile.connection.Connection;
import com.vh.basefile.console.Console;
import com.vh.basefile.dataset.Dataset;
import com.vh.basefile.dataset.DatasetHandler;
import com.vh.basefile.dataset.DatasetReader;
import com.vh.basefile.metadata.Database;
import com.vh.basefile.metadata.Row;
import com.vh.basefile.metadata.Table;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * put your comments here...
 * 
 */
public class Teste {
    
    private static final String USR_NAME = "MASTER";
    private static final String DB_NAME = "DATABASE"; 
    
    public static void main(String[] args) throws Exception {
    	
        Dataset ds = DatasetHandler.getDataset("TABLE");
        List l = DatasetReader.readTableData(ds);
        Iterator it = l.iterator();
        while (it.hasNext()) {        
            System.out.println(it.next());
        }
        DatasetHandler.closeDataset(ds);
    	
        //Teste ins = new Teste();
        //ins.getByValue("VALOR CAMPO 1");
        //ins.saveOrUpdate();
    }
    
    //===========================================
    
    public void getByValue(String value) throws Exception {
        Table tb = openTable("TABLE");
        List rows = tb.find("CAMPO1", value);
        Row row = (Row)rows.get(0);
        System.out.println(row.get("CAMPO1"));
        System.out.println(row.get("CAMPO2"));
        closeTable(tb);
    }
    
    public void saveOrUpdate() throws Exception {
        Table tb = openTable("TABLE");
        List rows = tb.find("ID", "1");
        
        Row row = null;
        // Entendendo que retorna 1 única linha
        if (!rows.isEmpty()) { row = (Row)rows.get(0); }
          else {row = tb.getRow(tb.newRow()); }

        row.update("CAMPO1", "TESTE CAMPO 1");
        row.update("CAMPO2", "TESTE CAMPO 2");
        tb.commitChanges();
        closeTable(tb);                
    }    
    
    //================================================
    
    private Database openDatabase(String usrName, String dbName) throws Exception {
        Console c = Console.getInstance();
        Connection con = c.openConnection(usrName);
        Database db = con.openDatabase(dbName);
        return db;        
    }
    
    public Table openTable(String tableName) throws Exception {
        Database db = openDatabase(USR_NAME, DB_NAME);
        Table tb = db.openTable(tableName);
        return tb;
    }
    
    public void closeTable(Table tb) throws Exception {
        Database db = openDatabase(USR_NAME, DB_NAME);
        db.closeTable(tb.getName());
    }    
}
