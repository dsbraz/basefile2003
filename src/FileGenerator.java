/*
 * Created on 12/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * put your comments here...
 * 
 */
public class FileGenerator {

    private static final String NOR_EXT = ".nbf";
    private static final String ZIP_EXT = ".zbf";
    
    public static void main(String[] args) {
        compress("SECURE");
        compress("USUARIOS");
        compress("CERTIFICADOS");
        compress("DOMINIOS");
        compress("ACOES");
        compress("ACOES_DOMINIOS");
        compress("ACOES_CERTIFICADOS");
        compress("SEQUENCES");
//        uncompress("SECURE");
//        uncompress("USUARIOS");
//        uncompress("CERTIFICADOS");
//        uncompress("DOMINIOS");
//        uncompress("ACOES");
//        uncompress("ACOES_DOMINIOS");
//        uncompress("ACOES_CERTIFICADOS");
//        uncompress("SEQUENCES");
    }
    
    public static synchronized void compress(String fileName) {
        try {
            String outFilename = fileName + ZIP_EXT;
            GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFilename));
    
            String inFilename = fileName + NOR_EXT;
            FileInputStream in = new FileInputStream(inFilename);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
    
            out.finish();
            out.close();
        } 
        catch (IOException e) {
        	e.printStackTrace();
        }       
    }
    
    public static synchronized void uncompress(String fileName) {     
        try {
            String inFilename = fileName + ZIP_EXT;
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(inFilename));
    
            String outFilename = fileName + NOR_EXT;            
            OutputStream out = new FileOutputStream(outFilename);
    
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
    
            in.close();
            out.close();
        } 
        catch (IOException e) {
        	e.printStackTrace();
        }       
    }    

}
