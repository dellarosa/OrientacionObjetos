package basics;

public class Main {

	public static void main(String [] args) {
		
		TableManager tm = new TableManager();
		tm.createUserTable();
		
		
		DataManager dm = new DataManager();
		
		String user = Dentre.texto("ingrese nombre usuario:");
		String email = Dentre.texto("ingrese email usuario:");
		String pass = Dentre.texto("ingrese password usuario:");
		
		dm.crearUsuario(user, email, pass);
		
		System.out.println("Ahora voy a mostrar el usuario recien cargado");
		String unUser = Dentre.texto("ingrese el username del usuario recien creado");
		dm.muestraUsuario(unUser);
		System.out.println("---------");
		
		System.out.println("Voy a modificar un usuario");
		String user2 = Dentre.texto("ingrese el nombre usuario a modificar:");
		String email2 = Dentre.texto("ingrese nuevo email:");
		String pass2 = Dentre.texto("ingrese nueva password:");
		dm.actualizaUsuario(user2, email2, pass2);
		
		System.out.println("Tengo estos usuarios:");
		dm.muestraTodosLosusuarios();
		System.out.println("------");
		
		
		System.out.println("Voy a borrar un usuario segun su username");
		String user3 = Dentre.texto("ingrese el nombre usuario a borrar:");
		dm.borraUsuario(user3);
		
		System.out.println("Tengo estos usuarios:");
		dm.muestraTodosLosusuarios();
		System.out.println("------");
		
		//tm.dropUserTable();
		
	}
	
}
