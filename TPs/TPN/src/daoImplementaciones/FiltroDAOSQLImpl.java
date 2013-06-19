package daoImplementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FiltroDAO;
import entities.Filtro;

import utils.DBManager;
import utils.MiException;


public class FiltroDAOSQLImpl implements FiltroDAO{

	//############################## CREATE TABLA FILTRO #####################################################################################
	public boolean crearTablaFiltro() throws MiException
	{
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

				query="CREATE TABLE Filtro (filtro_ID int NOT NULL PRIMARY KEY, autoparte_ID int NOT NULL,tamanio varchar(20) NOT NULL, material varchar(20) NOT NULL)";							
				stmt.executeUpdate(query);			
				conn.commit();
				
				System.out.print("\n[crearTablaFiltro] "+query);			//DEBUG 
			}catch(SQLException e)
			{
				System.out.print("\n[crearTablaFiltro] SQL Exception AL CREAR: "+e);			//DEBUG
				conn.rollback();
				//throw new MiException("[crearTablaFiltro] SQL Exception: "+e);
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[crearTablaFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[crearTablaFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[crearTablaFiltro] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}	
		return true;
	}
	
	//####################################3 CARGAR FILTROS ########################################
	public List<Filtro> cargarFiltros() throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;		
		List<Filtro> filtros=null;
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Filtro";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					filtros=new ArrayList<Filtro>();
					//System.out.print("\n[cargaFiltros] HAY Filtros ");		//DEBUG
					do {										
						Filtro filtro=new Filtro();
						filtro.setFiltro_ID(rs.getInt("filtro_ID"));					
						filtro.setId(rs.getInt("autoparte_ID"));
						filtro.setMaterial(rs.getString("material"));
						filtro.setTamanio(rs.getString("tamanio"));
						
						filtros.add(filtro);
					}while(rs.next());
				}else
				{	
					System.out.print("\n[cargaFiltros]NO HAY Filtros CARGADOS : ");		//DEBUG
				}
			}catch(SQLException e)
			{
				System.out.print("\n[cargaFiltros] SQL Exception al cargar: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[cargaFiltros] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[cargaFiltros] SQL EXCEPTION: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[cargaFiltros] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[cargaFiltros] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}	
		
		return filtros;
	}
	
	
		
	//############################## DELETE FILTRO ###################################################
	public boolean eliminarFiltro(Filtro filtro) throws MiException {
		
		boolean deleted=false;
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
				query="DELETE FROM Filtro WHERE filtro_ID="+filtro.getFiltro_ID();
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[eliminarFiltro] "+query);			//DEBUG
				deleted=true;
				
				
			}catch(SQLException e)
			{
				System.out.print("\n[eliminarFiltro] SQL Exception: "+e);		//DEBUG
				deleted=false;
				conn.rollback();
				//throw new MiException("[eliminarFiltro] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[eliminarFiltro] EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[eliminarFiltro] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[eliminarFiltro] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}
		return deleted;
	}
	
	//###################################### INSERTAR FILTRO #############################################################################
	public boolean insertarFiltro(int id,int autoparte_ID,String tamanio,String material) throws MiException
	{
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
				query="INSERT INTO Filtro (filtro_ID,autoparte_ID,tamanio,material) VALUES ("+id+",'"+autoparte_ID+"','"+tamanio+"','"+material+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarFiltro] "+query);				//DEBUG

			}catch(SQLException e)
			{
				System.out.print("\n[insertarFiltro] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[insertarFiltro] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}	
		return true;
	}

	public boolean insertarFiltro(Filtro filtro) throws MiException
	{
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
				query="INSERT INTO Filtro (filtro_ID,autoparte_ID,tamanio,material) VALUES ("+filtro.getFiltro_ID()+",'"+filtro.getId()+"','"+filtro.getTamanio()+"','"+filtro.getMaterial()+"')";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[insertarFiltro] "+query);					//DEBUG
			}catch(SQLException e)
			{
				System.out.print("\n[insertarFiltro] SQL Exception AL INSERTAR: "+e);					//DEBUG
				conn.rollback();	
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[insertarFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[insertarFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[insertarFiltro] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}	
		return true;
	}
	//###################################### MODIFICAR FILTRO #############################################################################
	public boolean updateFiltro(Filtro filtro) throws MiException
	{
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
				query="UPDATE Filtro SET filtro_ID='"+filtro.getFiltro_ID()+"',autoparte_ID='"+filtro.getId()+"',tamanio='"+filtro.getTamanio()+"',material='"+filtro.getMaterial()+"' WHERE filtro_ID='"+filtro.getFiltro_ID()+"'";
				stmt.executeUpdate(query);
				conn.commit();
				System.out.print("\n[updateFiltro] "+query);						//DEBUG
				
			}catch(SQLException e)
			{
				System.out.print("\n[updateFiltro] Exception SQL: "+e);					//DEBUG
				conn.rollback();
			}
		
		}catch(SQLException e)
		{
			throw new MiException("[updateFiltro]SQL Connection EXCEPTION "+e.getMessage());
		}catch(Exception e)
		{
		
			throw new MiException("[updateFiltro]ERROR AL CREAR TABLAS",e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[updateFiltro] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}
		return true;
	}

	//##################################### BUSCAR ULTIMO FILTRO ID ##########################################
	public int buscarUltimoFiltroId() throws MiException
	{
		int inLastID = 0;
		String query;						
		Connection conn=null;
		Statement stmt=null;
		
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{	
				query="SELECT filtro_ID FROM Filtro ORDER BY filtro_ID DESC LIMIT 1";
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				if(rs.next()) {									
					inLastID=rs.getInt("filtro_ID");
					inLastID++;
				//	System.out.print("\n[buscarUltimoFiltroId] LAST ID: "+inLastID);	//DEBUG
				}else
				{	
					//System.out.print("\n[buscarUltimoFiltroId] NO HAY COINCIDENCIAS");	//DEBUG
					//inLastID=-1;
					inLastID=1;
					
				}	
			}catch(SQLException e)
			{
				System.out.print("\n[buscarUltimoFiltroId] SQL Exception: "+e);		//DEBUG				
				conn.rollback();
				//throw new MiException("[buscarUltimoFiltroId] SQL Exception: "+e);
			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarUltimoFiltroId] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarUltimoFiltroId] EXCEPTION AL CONECTAR: "+e);
		}
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[buscarUltimoFiltroId] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}
		
		return inLastID;
	}
	
	//################################### BUSCAR FILTRO POR ID AUTOPARTE ##################################################################
	public Filtro buscarFiltroPorIdAutoParte(int id) throws MiException
	{
		String query;
		Connection conn=null;
		Statement stmt=null;
		Filtro filtro=null;
		
		try
		{			
			conn = DBManager.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{				
				query="SELECT * FROM Filtro WHERE autoparte_ID="+id;
				ResultSet rs=stmt.executeQuery(query);			
				conn.commit();
				
				if(rs.next())
				{
					//System.out.print("\n[buscarFiltroPorId] HAY Filtro ");		//DEBUG
					do {										
						filtro=new Filtro();
						filtro.setFiltro_ID(rs.getInt("filtro_ID"));					
						filtro.setId(rs.getInt("autoparte_ID"));
						filtro.setMaterial(rs.getString("material"));
						filtro.setTamanio(rs.getString("tamanio"));
						
					}while(rs.next());
				}else
				{	
					//System.out.print("\n[buscarFiltroPorIdAutoParte]NO HAY Filtros CARGADOS : ");		//DEBUG
				}
			}catch(SQLException e)
			{
				//System.out.print("\n[main] SQL Exception: "+e);		//DEBUG
				conn.rollback();
				//throw new MiException("[Login] SQL Exception: "+e);

			}						
		}catch(SQLException e)
		{
			throw new MiException("[buscarFiltroPorIdAutoParte] SQL EXCEPTION AL CONECTAR: "+e);
		}
		catch(Exception e)
		{
			throw new MiException("[buscarFiltroPorIdAutoParte] EXCEPTION AL CONECTAR: "+e);
		}		
		finally
		{
			try {
				stmt.execute("SHUTDOWN");
				conn.close();
			} catch (SQLException e) {
				throw new MiException("[buscarFiltroPorIdAutoParte] Exception Close: " +e);
			}							//CIERRO STATEMENT
				
						
		}
		return filtro;
	}
}
