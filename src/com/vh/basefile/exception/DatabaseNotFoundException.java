/*
 * Created on 09/02/2004
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
 * exceção para qdo o database não puder ser encontrado
 * 
 */
public class DatabaseNotFoundException extends DatabaseException {
	
	public DatabaseNotFoundException(String message) {
		super(message);
	}
	
}
