/*
 * Created on 11/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.exception;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa uma exceção para leitura
 * dos datasets
 */
public class ReadDatasetException extends DatasetException {
	
	public ReadDatasetException(String message) {
		super(message);
	}
	
}
