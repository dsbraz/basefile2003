/*
 * Created on 12/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.metadata;

/*
 * Author Daniel S. Braz 
 *        dsbraz@yahoo.com.br
 *
 * define uma row
 */
public interface Row extends Metadata {
	
	public int status();
	
	public Object get(String column);
		
	public void update(String column, Object value);

}
