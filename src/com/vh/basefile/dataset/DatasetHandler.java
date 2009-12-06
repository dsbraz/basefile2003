/*
 * Created on 10/02/2004
 * 
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.dataset;

import com.vh.basefile.exception.DatasetException;
import com.vh.basefile.exception.DatasetNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/*
 * Author Daniel S. Braz dsbraz@yahoo.com.br
 * 
 * implementa classe pra manipulação dos arquivos de persistencia
 */
public class DatasetHandler {

    private static final String ROOT_PATH = "//database//";
    private static final String NOR_EXT = ".nbf";
    private static final String ZIP_EXT = ".zbf";

    private DatasetHandler() {
        //
    }

    public static Dataset getDataset(String dsName)
      throws DatasetNotFoundException, DatasetException {
        try {
            // DEBUG -- DatasetHandler:getDataset
            System.out.println("DatasetHandler:getDataset:" + dsName);
            uncompress(findResourcePath(dsName + ZIP_EXT));
            Dataset ds = new Dataset(findResourcePath(dsName + NOR_EXT), "rw");
            return ds;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DatasetNotFoundException(e.getMessage());
        }
    }

    public static synchronized void closeDataset(Dataset ds)
      throws DatasetException {
        try {
            // DEBUG -- DatasetHandler:CloseDataset
            System.out.println("DatasetHandler:CloseDataset:"
                    + ds.getDataSetName());
            ds.close();
            new File(ds.getDataSetName()).delete();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatasetException(e.getMessage());
        }
    }

    public static void saveDataset(Dataset ds) throws DatasetException {
        // DEBUG -- DatasetHandler:SaveDataset
        System.out.println("DatasetHandler:SaveDataset:" + ds.getDataSetName());
        compress(ds.getDataSetName());
    }

    private static synchronized void compress(String dsName)
      throws DatasetException {
        try {
            // DEBUG -- DatasetHandler:Compress
            System.out.println("DatasetHandler:Compress:" + dsName);
            String ofn = dsName.substring(0, dsName.lastIndexOf('.')) + ZIP_EXT;
            FileOutputStream fos = new FileOutputStream(ofn);
            GZIPOutputStream gos = new GZIPOutputStream(fos);
            InputStream is = new FileInputStream(dsName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                gos.write(buf, 0, len);
            }
            is.close();
            gos.finish();
            gos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatasetException(e.getMessage());
        }
    }

    private static synchronized void uncompress(String dsName)
      throws DatasetException {
        try {
            // DEBUG -- DatasetHandler:Uncompress
            System.out.println("DatasetHandler:Uncompress:" + dsName);
            FileInputStream fis = new FileInputStream(dsName);
            GZIPInputStream gis = new GZIPInputStream(fis);
            String ofn = dsName.substring(0, dsName.lastIndexOf('.')) + NOR_EXT;
            OutputStream os = new FileOutputStream(ofn);
            byte[] buf = new byte[1024];
            int len;
            while ((len = gis.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            gis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatasetException(e.getMessage());
        }
    }

    private static String findResourcePath(String s) {
        // DEBUG -- DatasetHandler:findResourcePath
        System.out.println(" >> CurrentThread:" + Thread.currentThread());
        System.out.println(" >> ContextClassLoader:" + Thread.currentThread().getContextClassLoader());
        System.out.println(" >> ResourcePath:" + Thread.currentThread().getContextClassLoader().getResource(ROOT_PATH + s).getFile());
        return Thread.currentThread().getContextClassLoader().getResource(ROOT_PATH + s).getFile();
    }

}