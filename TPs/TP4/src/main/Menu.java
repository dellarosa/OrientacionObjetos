package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import entities.Autoparte;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;

import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;

public class Menu {
	
	MenuPrincipal menuprinc;
	MetodosGrl metgral;
	public Menu()
	{
		menuprinc=new MenuPrincipal();
	}
	public MenuPrincipal getMenuPrincipal()
	{
		return this.menuprinc;
	}
	//////////////
	public class MenuPrincipal
	{
		boolean sigo;
		
		public MenuPrincipal()
		{
			metgral=new MetodosGrl();
		}
		public String empezarMenu() throws Exception
		{
			Connection conn;
			Statement stmt;
			String query=null;
			sigo=false;
			
			while(!sigo)
			{
				try
				{
					Class.forName(Definiciones.DB_DRIVER);
					conn = DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
				}catch(SQLException e)
				{
					throw new MiException("Error de conexión SQL");
				}
				
				System.out.print("\n1-CARGAR CLIENTE");
				System.out.print("\n2-CARGAR AUTOPARTE");
				System.out.print("\n3-CARGAR REPARACION");
				System.out.print("\n4-FINALIZAR REPARACION");
				System.out.print("\n5-MOSTRAR DATOS");
				System.out.print("\n94-CARGAR USUARIO");		//SI ES ADMINISTRADOR
				System.out.print("\n98-CAMBIAR DE USUARIO");
				System.out.print("\n99-SALIR");
				
				int opcion=Dentre.entero("\nINGRESE OPCION:");
				
				switch(opcion)
				{
					case 1:
						try
						{
							Cliente cliente= new Cliente();
							cliente.setNombre(Dentre.texto("\nINGRESE NOMBRE: "));
							cliente.setMail(Dentre.texto("\nINGRESE MAIL: "));
							
							query="INSERT INTO Clientes (cliente_ID,name,mail,auto) VALUES (1,'Pepe','pepearrobavida.com','gol power')";
							stmt.executeUpdate(query);
							conn.commit();
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						
						break;
					case 2:
												
						break;
					case 3:
						try
						{
							Reparacion reparacion=new Reparacion();							
							reparacion.setCliente(metgral.obtenerCliente());
							reparacion.setUsuario(metgral.obtenerUsuarioActual());
							Calendar c = new GregorianCalendar();
							reparacion.setFechainicio(c.getTime().toString());
							
							query="INSERT INTO Usuarios (U_Id,name,mail,user,pass) VALUES (1,'Pepe','pepearrobavida.com','pepito','12341234')";
							stmt.executeUpdate(query);
							conn.commit();
							
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						break;
					case 4:
						try
						{
							Reparacion reparacion=metgral.obtenerReparacion();
							Autoparte[] autoparteutil=metgral.obtenerAutopartesEnSistema();
							boolean fin=false;
							int x=0;
							while(x<autoparteutil.length)
							{
								System.out.print("\n"+x+1+"-AUTOPARTE: "+autoparteutil[x].getClass().getCanonicalName());
								x++;
							}
							while(!fin)					
							{
															
							}
							
							Dentre.entero("\nINGRESE AUTOPARTES UTILIZADAS: ");
							Autoparte autoparte=new Autoparte();
							
							reparacion.setAutopartes(autoparteutil);
							reparacion.setCosto(metgral.obtenerCostoAutopartes(autoparteutil));
							
							
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						break;
					case 5:
						
						break;
					case 94:
							//SI ES ADMINISTRADOR CARGA USUARIO
						try
						{
							Usuario usuario=new Usuario();
							usuario.setEmail(Dentre.texto("\nINGRESE MAIL: "));
							usuario.setName(Dentre.texto("\nINGRESE NOMBRE: "));
							usuario.setUsername(Dentre.texto("\nINGRESE USER: "));
							usuario.setPassword(Dentre.texto("\nINGRESE PASS: "));
							
							query="INSERT INTO Usuarios (U_Id,name,mail,user,pass) VALUES (1,"+usuario.getName()+","+usuario.getEmail()+","+usuario.getUsername()+","+usuario.getPassword()+")";
							stmt.executeUpdate(query);
							conn.commit();
							System.out.print("\n[main] INSERTE: "+query);
						}catch(SQLException e)
						{
							e.printStackTrace();
						}

						break;
					case 98:
							sigo=true;
						break;
					case 99:
													
							return "salir";
						
					default:
						break;
				}
				
				stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
				conn.close();										//CIERRO BD			
				//System.exit(0);				
			}
			return "OK";
		}
		
	}
}
