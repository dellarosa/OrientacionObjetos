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
import entities.SQLClass;
import entities.Usuario;

import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;

public class Menu {
	
	MenuPrincipal menuprinc;
	MetodosGrl metgral;
	MenuInicio menuinicio;
	
	public Menu()
	{
		menuprinc=new MenuPrincipal();
		menuinicio=new MenuInicio();
		
	}
	public MenuPrincipal getMenuPrincipal()
	{
		return this.menuprinc;
	}
	//////////////
	public MenuInicio getMenuInicio()
	{
		return this.menuinicio;
	}
	public class MenuPrincipal
	{
		boolean sigo;
		
		public MenuPrincipal()
		{
			metgral=new MetodosGrl();
		}
		public String empezarMenu(Usuario user) throws Exception
		{
			Connection conn;
			Statement stmt;
			String query=null;
			sigo=false;
			
			while(!sigo)
			{
				try
				{
					conn = DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
				}catch(SQLException e)
				{
					throw new MiException("Error de conexión SQL");
				}
				
				System.out.print("\n1-CARGAR CLIENTE");
				System.out.print("\n1-MODIFICACION CLIENTE");
				System.out.print("\n1-BAJA CLIENTE");
				System.out.print("\n2-CARGAR AUTOPARTE");
				System.out.print("\n2-MODIFICACION AUTOPARTE");
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
							
							////
							query="SELECT * FROM Usuarios";							
							ResultSet rs=stmt.executeQuery(query);							
							conn.commit();
							System.out.print("\n[main] Usuarios: "+rs.getInt("U_Id")+"\nUser: "+rs.getString("user")+"\nMail: "+rs.getString("mail"));
							
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
	//#######################################################################################
	public class MenuInicio
	{
		public MenuInicio()
		{
			metgral=new MetodosGrl();
		}
		public Usuario empezarMenuInicio(Usuario[] usuarios) throws MiException
		{
			boolean salir=false;
			String user;
			String pass;
			boolean login=false;
			Usuario usuario=new Usuario();
			while(!salir) //GENERAL
			{
				
								
				System.out.print("\n\n****BIENVENIDO TALLER 2013****");
				System.out.print("\n1-CREAR USUARIO");
				System.out.print("\n2-MODIFICAR USUARIO");
				System.out.print("\n3-BAJA USUARIO");
				System.out.print("\n4-LOGUEARSE AL SISTEMA");
				System.out.print("\n99-SALIR");
				switch(Dentre.entero("\n\n INGRESE OPCIÓN: "))
				{
					case 1:
						try
						{
							Usuario userCreate=new Usuario();
							
							userCreate.setName(Dentre.texto("\n INGRESE NOMBRE: "));
							userCreate.setEmail(Dentre.texto("\n INGRESE MAIL: "));							
							userCreate.setUsername(Dentre.texto("\n INGRESE APODO: "));							
							userCreate.setPassword(Dentre.texto("\n INGRESE PASSWORD: "));							
							userCreate.setJerarquia(Dentre.texto("\n INGRESE JERARQUIA: "));
							userCreate.setId(metgral.buscarUltimoUsuario(usuarios)+1);
							if(metgral.crearUsuario(userCreate))
							{
								System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO MODIFICACION USUARIO");
								Thread.sleep(2000);
							}
						}
						catch(Exception e)
						{							
							throw new MiException("CREATE USER EXCEPTION: "+e);
						}
						break;
					case 2:
							try
							{
								Usuario usuarioBaja=metgral.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A DAR DE BAJA: "));
								if(metgral.eliminarUsuario(usuarioBaja))
								{
									System.out.print("\nUSUARIO ELIMINADO CORRECTAMENTE");
									Thread.sleep(2000);
								}else
								{
									System.out.print("\nFALLO BAJA USUARIO");
									Thread.sleep(2000);
								}	
							}
							catch(Exception e)
							{							
								throw new MiException("DELETE USER EXCEPTION: "+e);
							}	
														
						break;
					case 3:
							
						try
						{
							Usuario usuarioModif=metgral.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A MODIFICAR: "));
							if(usuarioModif==null)
							{
								System.out.print("\nNO SE ENCONTRO EL USUARIO");
								Thread.sleep(2000);
							}else
							{
								Usuario userAux=new Usuario();
								userAux.setName(Dentre.texto("\n INGRESE NOMBRE A MODIFICAR: "));								
								userAux.setEmail(Dentre.texto("\n INGRESE MAIL A MODIFICAR: "));								
								userAux.setUsername(Dentre.texto("\n INGRESE APODO A MODIFICAR: "));								
								userAux.setPassword(Dentre.texto("\n INGRESE PASSWORD USUARIO: "));
								userAux.setJerarquia(Dentre.texto("\n INGRESE JERARQUIA USUARIO: "));						
								userAux.setId(usuarioModif.getId());
								
								if(metgral.crearUsuario(userAux))
								{
									System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
									Thread.sleep(2000);
								}else
								{
									System.out.print("\nFALLO MODIFICACION USUARIO");
									Thread.sleep(2000);
								}
							}
						}catch(Exception e)
						{
							throw new MiException("MODIFICACION USER EXCEPTION: "+e);							
						}
						break;
					case 4:			
						try
						{
							user=Dentre.texto("\n INGRESE SU USUARIO: ");
							pass=Dentre.texto("\nINGRESE SU CONTRASEÑA: ");		
													
							if((usuario=metgral.loginUser(user,pass))==null)
							{
								System.out.print("\nUSUARIO O CONTRASEÑA INCORRECTA, VUELVA A INGRESAR");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nUSUARIO LOGUEADO");
								Thread.sleep(2000);
								login=true;
								return usuario;
							}
						}catch(Exception e)
						{							
							throw new MiException("LOGIN USER EXCEPTION: "+e);
						}
						
					break;
					case 99:
						return null;						
						
					default:
						break;
				}
				
			}
			return null;
		}
	}
}
