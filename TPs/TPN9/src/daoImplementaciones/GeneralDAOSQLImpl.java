package daoImplementaciones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import utils.DBManager;
import utils.MiException;
import dao.AceiteDAO;
import dao.AutoparteDAO;
import dao.ClienteDAO;
import dao.FiltroDAO;
import dao.GeneralDAO;
import dao.LamparaDAO;
import dao.ReparacionDAO;
import dao.UsuarioDAO;

public class GeneralDAOSQLImpl implements GeneralDAO 
{


	
////################################################# CREAR TABLAS ##################################################################
	public void crearTablas() throws MiException
	{
		
		
		try
		{
		
			//########################## USUARIOS #################################
			UsuarioDAO usuarioDao=new UsuarioDAOSQLImpl();
			usuarioDao.crearTablaUsuario();
			usuarioDao.insertarUsuario(1,"pepe","pepearrobamail.com", "admin","admin",0,"administrador");
			
			//########################## AUTOPARTES #################################
			AutoparteDAO autoparteDao=new AutoparteDAOSQLImpl();
			
			autoparteDao.crearTablaAutoparte();
			autoparteDao.insertarAutoparte(1,"filtro","Filtros2000","F2000",30,4);
			autoparteDao.insertarAutoparte(2,"aceite","aceites2000","A2000",68,5);
			autoparteDao.insertarAutoparte(3,"lampara","lamparas","L2000",20,3);
			
			//########################## FILTROS #################################
			FiltroDAO filtroDao=new FiltroDAOSQLImpl();
			
			filtroDao.crearTablaFiltro();
			crearIndex("autoparteFiltro_ID","Filtro","autoparte_ID");
			filtroDao.insertarFiltro(1,1,"grande","plastico");				
			
			//########################## ACEITES #################################
			AceiteDAO aceiteDao=new AceiteDAOSQLImpl();
			aceiteDao.crearTablaAceite();
			crearIndex("autoparteAceite_ID","Aceite","autoparte_ID");								
			
			aceiteDao.insertarAceite(1,2,4,"sintetico");
		

			//########################## LAMPARAS #################################
			LamparaDAO lamparaDao=new LamparaDAOSQLImpl();
			lamparaDao.crearTablaLampara();
			crearIndex("autoparteLampara_ID","Lampara","autoparte_ID");						
			lamparaDao.insertarLampara(1,3,"blanca","mediana");		
			
			//########################## CLIENTE #################################
			ClienteDAO clienteDao=new ClienteDAOSQLImpl();
			clienteDao.crearTablaCliente();
			
			clienteDao.insertarCliente(1, "PEPe", "GOL POWER","pepearrobapepe.com","ADT 727");
			
			//########################## REPARACIONES AUTOPARTES #################################
			ReparacionDAO reparacionDao=new ReparacionDAOSQLImpl();
			reparacionDao.crearTablaReparacionAutoparte();
			crearIndex("reparaciones_ID","ReparacionAutoparte","reparacion_ID");
						
			crearIndex("autoparte_ID","ReparacionAutoparte","autoparte_ID");			

			//########################## REPARACIONES #################################
			reparacionDao.crearTablaReparacion();
			//sqlCreateIndex.crearIndex("reparacionAutoparte_ID","Reparacion","reparacionAutoparte_ID");
			crearIndex("cliente_ID","Reparacion","cliente_ID");
			
		}catch(MiException e)
		{
			System.out.print("\n[GENERAL_DAO][crearTablas] SQLException CREANDO TABLAS: "+e);		//DEBUG					
			throw new MiException("[crearTablas]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
				
			throw new MiException("[crearTablas]ERROR AL CREAR TABLAS",e);
		}	
	}
	//####################################### CREAR INDEX ############################################
	public boolean crearIndex(String index,String tabla,String indextabla) throws MiException{
		String query;
		Connection conn=null;
		Statement stmt = null;
		try
		{
			
			conn=DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		
			try
			{					
				query="CREATE INDEX "+index+" ON "+tabla+" ("+indextabla+")";							
				stmt.executeUpdate(query);			
				conn.commit();				
				System.out.print("\n[crearIndex] "+query);				//DEBUG
				
				
			}catch(SQLException e)
			{	
				System.out.print("\n[crearIndex]SQLCrearIndex Exception: "+e);		//DEBUG
				conn.rollback();	
			}
		
			
		}catch(SQLException e)
		{
			throw new MiException("[crearIndex]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearIndex]ERROR AL CREAR INDEX",e);
		}	
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {

			}
				
		}
		return true;
		
	}
}
