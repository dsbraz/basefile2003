/*
 * Created on 10/02/2004
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
 * exceção para indicar uma operação não suportada
 */
public class OperationNotSuportedException extends Exception {
	
	public OperationNotSuportedException(String message) {
		super(message);
	}

}
