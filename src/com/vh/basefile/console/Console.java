/*
 * Created on 09/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vh.basefile.connection.Connection;
import com.vh.basefile.connection.ConnectionImpl;
import com.vh.basefile.exception.InvalidUserException;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * implementa um console para administração
 * e controle das operações dos usuários
 * 
 */
public class Console {
	
	private static Console instance;
	private Map connections;
	
	private Console() {
		connections = new HashMap();
	}
	
	public static Console getInstance() {
		if (instance == null) {
			instance = new Console();
		}
		return instance;
	}
	
	public Connection openConnection(String user) throws InvalidUserException {
        if (!connections.containsKey(user)) {
            Connection conn = new ConnectionImpl(user);
            connections.put(user, conn);
        }
        return (Connection)connections.get(user);
	}
	
	public void closeConnection(String user) {
		connections.remove(user);
	}
	
	public int getStatistic() {
		return connections.size();
	}
	
	public List getConnectedUsers() {		
		return new ArrayList(connections.keySet());
	}
	
	public boolean userIsConnected(String user) {
		return connections.containsKey(user);
	}

}
