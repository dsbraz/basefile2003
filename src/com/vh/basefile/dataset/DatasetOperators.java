/*
 * Created on 16/02/2004
 *
 * Free Software - GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 * VanHelsin® Software 2004
 * vanhelsin@hotmail.com
 */
package com.vh.basefile.dataset;

/*
 * Author Daniel S. Braz
 *        dsbraz@yahoo.com.br
 *
 * define as operações básicas de 
 * manipulação de IO para um Dataset 
 */
public interface DatasetOperators {

    static final String BOF = "[FILE]"; // Inicio do arquivo
    static final String EOF = "[\\FILE]"; // Final do arquivo
    static final String BOB = "[DATABASE]"; // Inicio do banco de dados
    static final String EOB = "[\\DATABASE]"; // Final do banco de dados
    static final String BOT = "[TABLE]"; // Inicio da tabela
    static final String EOT = "[\\TABLE]"; // Final da tabela

    static final String AOP = "="; // Operador de atribuição
    static final String SOP = ","; // Operador de separação

    static final String EOD = "\\&eod"; // Término da informação
    static final char CRLF = '\n'; // Término da linha

    static final String NULL = "NULL"; // Substituido em valores nulls

    static final String NAME = "NAME"; // Nome (banco e/ou tabela)
    static final String SELI = "SECURITY_LIST"; // Lista de seguraça
    static final String DEFS = "DEF"; // Definições (banco e/ou tabela)
    static final String DATA = "DATA"; // Dados

}