package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Reparacion;
import entities.SQLCarga;
import entities.SQLDelete;
import entities.SQLInserts;
import entities.SQLSelects;
import entities.Usuario;

import utils.Definiciones;
import utils.Dentre;
import utils.MetodosGrl;
import utils.MiException;

public class Menu {
	
	MenuPrincipal menuprinc;
	MetodosGrl metgral;
	MenuInicio menuinicio;
	
	SQLInserts sqlinserts=new SQLInserts();
	SQLSelects sqlselects=new SQLSelects();
	SQLDelete sqldelete=new SQLDelete();
	SQLCarga sqlcarga =new SQLCarga();
	
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
		public String empezarMenu(Usuario user) throws Exception,MiException
		{
			Connection conn;
			Statement stmt;			
			
			Autoparte[][] autopartesG=new Autoparte[][]{};
			Cliente[] clientesG=new Cliente[]{};
			Reparacion[] reparacionesG=new Reparacion[]{};
			//Filtro[] filtrosG=new Filtro[]{};
			//Lampara[] lamparasG=new Lampara[]{};
			//Aceite[] aceitesG=new Aceite[]{};
			
			sigo=false;
			while(!sigo)
			{
				
				try
				{
					sqlcarga.cargaAutopartes(autopartesG);
					sqlcarga.cargaClientes(clientesG);
					sqlcarga.cargaReparaciones(reparacionesG);
					//sqlcarga.cargarFiltros(filtrosG);
					//sqlcarga.cargarAceites(aceitesG);
					//sqlcarga.cargarLamparas(lamparasG);
					
					
				}catch(SQLException e)
				{
					throw new MiException("Error de conexión SQL");
				}
				
				System.out.print("\n1-CARGAR CLIENTE");
				System.out.print("\n2-MODIFICACION CLIENTE");
				System.out.print("\n3-BAJA CLIENTE");
				System.out.print("\n4-CARGAR AUTOPARTE");
				System.out.print("\n5-MODIFICACION AUTOPARTE");
				System.out.print("\n6-BAJA AUTOPARTE");
				System.out.print("\n7-CARGAR REPARACION");
				System.out.print("\n8-FINALIZAR REPARACION");
				System.out.print("\n9-VER REPARACIONES");				
				System.out.print("\n10-MOSTRAR DATOS");				
				
				//System.out.print("\n11-CALCULAR ");		//SI ES ADMINISTRADOR
				System.out.print("\n98-CAMBIAR DE USUARIO");
				System.out.print("\n99-SALIR");
				
				int opcion=Dentre.entero("\nINGRESE OPCION:");
				
				switch(opcion)
				{
					case 1:
						try
						{
							conn = DriverManager.getConnection(Definiciones.DB_URL, Definiciones.DB_USERNAME, Definiciones.DB_PASSWORD);
							conn.setAutoCommit(false);
							stmt = conn.createStatement();
							
							Cliente cliente= new Cliente();
							cliente.setId(metgral.buscarUltimoCliente(clientesG));
							cliente.setNombre(Dentre.texto("\nINGRESE NOMBRE: "));
							cliente.setMail(Dentre.texto("\nINGRESE MAIL: "));
							cliente.setAuto(Dentre.texto("\nINGRESE AUTO: "));
							
							if(sqlinserts.insertarCliente(cliente))
							{
								System.out.print("\nCLIENTE INGRESADO CORRECTAMENTE");
								Thread.sleep(2000);
							}else
							{
								System.out.print("\nFALLO INGRESO CLIENTE");
								Thread.sleep(2000);
							}
							
							
							stmt.execute("SHUTDOWN");							//CIERRO STATEMENT
							conn.close();										//CIERRO BD
							
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						
						break;
					case 2:
							try
							{
								Cliente cliente=sqlselects.buscarClientePorApodo(Dentre.texto("\n INGRESE APODO USUARIO A DAR DE BAJA: "));
								if(sqldelete.eliminarCliente(cliente))
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
						
						break;
					case 4:
						try
						{							
							//PODRIA SER MAS DINAMICO
							System.out.print("\n1-FILTROS");	
							System.out.print("\n2-ACEITE");
							System.out.print("\n3-LAMPARA");
							System.out.print("\n4-NUEVA AUTOPARTE");
							switch(Dentre.entero("\nINGRESE TIPO AUTOPARTE: "))
							{
								case Definiciones.FILTRO_INDICE:
									Filtro filtro =new Filtro();
									
									filtro.setId(metgral.buscarUltimaAutoparteId(autopartesG[Definiciones.FILTRO_INDICE]));
									filtro.setMaterial(Dentre.texto("\nINGRESE TIPO MATERIAL: "));
									filtro.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									filtro.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									filtro.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									filtro.setTipoAutoparte("filtro");
									filtro.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									filtro.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									filtro.setAutoparteID(metgral.buscarUltimaAutoparte(autopartesG));
									sqlinserts.insertarFiltro(filtro);
									sqlinserts.insertarAutoparte(filtro.getAutoparteID(),filtro.getTipoAutoparte(),filtro.getMarca(),filtro.getModelo(),filtro.getCosto(),filtro.getCantDisponible());
										
									break;
									
								case Definiciones.ACEITE_INDICE:
									Aceite aceite =new Aceite();
									
									aceite.setId(metgral.buscarUltimaAutoparteId(autopartesG[Definiciones.ACEITE_INDICE]));
									aceite.setCantidadlitros(Dentre.entero("\nINGRESE CANTIDAD LITROS: "));
									aceite.setTipoAceite(Dentre.texto("\nINGRESE TAMAÑO: "));
									aceite.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									aceite.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									aceite.setTipoAutoparte("filtro");
									aceite.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									aceite.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									aceite.setAutoparteID(metgral.buscarUltimaAutoparte(autopartesG));
									sqlinserts.insertarAceite(aceite);
									sqlinserts.insertarAutoparte(aceite.getAutoparteID(),aceite.getTipoAutoparte(),aceite.getMarca(),aceite.getModelo(),aceite.getCosto(),aceite.getCantDisponible());
									break;
									
								case Definiciones.LAMPARA_INDICE:
									Lampara lampara =new Lampara();
									
									lampara.setId(metgral.buscarUltimaAutoparteId(autopartesG[Definiciones.LAMPARA_INDICE]));
									lampara.setColor(Dentre.texto("\nINGRESE CANTIDAD LITROS: "));
									lampara.setTamaño(Dentre.texto("\nINGRESE TAMAÑO: "));
									lampara.setMarca(Dentre.texto("\nINGRESE MARCA: "));
									lampara.setModelo(Dentre.texto("\nINGRESE MODELO: "));
									lampara.setTipoAutoparte("filtro");
									lampara.setCantDisponible(Dentre.entero("\nINGRESE CANTIDAD: "));
									lampara.setCosto(Dentre.doble("\nINGRESE COSTO: "));
									lampara.setAutoparteID(metgral.buscarUltimaAutoparte(autopartesG));
									sqlinserts.insertarLampara(lampara);
									sqlinserts.insertarAutoparte(lampara.getAutoparteID(),lampara.getTipoAutoparte(),lampara.getMarca(),lampara.getModelo(),lampara.getCosto(),lampara.getCantDisponible());
									
									break;
								
								case 4:
									break;
								default:
									break;
							}
							
						}catch(SQLException e)
						{
							throw new MiException("[CARGAR AUTOPARTE] SQLException : "+e);
						}catch(Exception e)
						{
							throw new MiException("[CARGAR AUTOPARTE] Exception : "+e);
						}
						break;
					case 5:
						
						break;
					case 94:
						
						break;
					case 98:
							return "mudar";
					case 99:
													
							return "salir";
						
					default:
						break;
				}
							
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
								Usuario usuarioBaja=sqlselects.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A DAR DE BAJA: "));
								if(sqldelete.eliminarUsuario(usuarioBaja))
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
							Usuario usuarioModif=sqlselects.buscarUsuarioPorApodo(Dentre.texto("\n INGRESE APODO USUARIO A MODIFICAR: "));
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
