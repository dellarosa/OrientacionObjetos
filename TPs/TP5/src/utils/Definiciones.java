package utils;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class Definiciones {


	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	//public static final String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
	public static final String DB_URL = "jdbc:hsqldb:file:C:\\Test\\TP5\\filedb";
	//public static final String DB_URL = "jdbc:hsqldb:file:testdb";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "";
	
	public static final int FILTRO_INDICE=1;
	public static final int ACEITE_INDICE=2;
	public static final int LAMPARA_INDICE=3;
	
	public static final String FILTRO_STRING="filtro";
	public static final String ACEITE_STRING="aceite";
	public static final String LAMPARA_STRING="lampara";
	
	public static final int PASS_TECNICO=9999;
	
	public static final int MODO_TECNICO=1;
	public static final int MODO_SISTEMA=2;
	
	public static final int MODO_SALIR=99;
	
	public static final Border line_blackline = BorderFactory.createLineBorder(Color.black);
	public static final Border line_raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	public static final Border line_loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	public static final Border line_raisedbevel = BorderFactory.createRaisedBevelBorder();
	public static final Border line_loweredbevel = BorderFactory.createLoweredBevelBorder();
	public static final Border line_empty = BorderFactory.createEmptyBorder();
	public static final int cantidadOpcionesMenu = 10;
	
	
}
