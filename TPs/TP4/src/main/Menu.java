package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Dentre;

public class Menu {
	
	MenuPrincipal menuprinc;
	
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
			
		}
		public void empezarMenu(Statement stmt,Connection conn)
		{
			String query=null;
			sigo=false;
			while(!sigo)
			{				
				System.out.print("\n1-CREAR DB");
				System.out.print("\n2-INSERTAR DATOS");
				System.out.print("\n3-BUSCAR POR FILTRO");
				System.out.print("\n4-BUSCAR TODO");
				int opcion=Dentre.entero("\nINGRESE OPCION:");
				
				switch(opcion)
				{
					case 1:
						try
						{
							 query="CREATE TABLE Usuarios(U_Id int NOT NULL PRIMARY KEY,name varchar(40) NOT NULL,mail varchar(40) NOT NULL,user varchar(12) NOT NULL,pass varchar(8) NOT NULL)";
							
							//Dentre.texto("[main] CREAR TABLA? "+query);
							System.out.print("[main] CREAR TABLA: "+query);
							stmt.executeUpdate(query);
							conn.commit();
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						
						break;
					case 2:
						try
						{
							query="INSERT INTO Usuarios (U_Id,name,mail,user,pass) VALUES (1,'Pepe','pepearrobavida.com','pepito','12341234')";
							stmt.executeUpdate(query);
							conn.commit();
							System.out.print("\n[main] INSERTE: "+query);
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						
						break;
					case 3:
						try
						{
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
							query="SELECT * FROM Usuarios";							
							ResultSet rs=stmt.executeQuery(query);							
							conn.commit();
							System.out.print("\n[main] Usuarios: "+rs.getInt("U_Id")+
									"\nUser: "+rs.getString("user")+"\nMail: "+rs.getString("mail"));
							
						}catch(SQLException e)
						{
							e.printStackTrace();
						}
						break;
					default:
						break;
				}
				
				
				
			}
		}
	}
}
