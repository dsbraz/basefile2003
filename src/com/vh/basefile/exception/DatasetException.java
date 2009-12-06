/*
 * Created on 10/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.exception;

import java.io.IOException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * exceção genérica para os dataset
 * 
 */
public class DatasetException extends IOException {
	
	public DatasetException(String message) {
		super(message);
	}

}
