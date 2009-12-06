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
 * exceção genérica para os databases
 * 
 */
public class DatabaseException extends Exception {
	
    public DatabaseException(String message) {
    	super(message);
    }
	
}
