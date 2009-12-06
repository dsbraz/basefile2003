/*
 * Created on 10/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.dataset;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * Implementa o tipo de arquivo
 * a ser usado pelo Basefile como
 * resultado (DataSet)
 * 
 */
public class Dataset extends RandomAccessFile {
	
	private String dsName;

	Dataset(String name, String mode) throws FileNotFoundException {
    	super(name, mode);
		this.dsName = name;
	}
	
	String getDataSetName() {
		return this.dsName;
	}

}
