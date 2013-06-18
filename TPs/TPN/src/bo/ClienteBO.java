package bo;

import java.util.List;

import utils.MiException;
import dao.ClienteDAO;
import daoImplementaciones.ClienteDAOSQLImpl;
import entities.Cliente;

public class ClienteBO {
	
	private ClienteDAO clienteDao;

	public ClienteBO()
	{
		clienteDao=new ClienteDAOSQLImpl();
	}
	
	public List<Cliente> cargaClientes() throws MiException {
		return clienteDao.cargaClientes();
	}

	public boolean crearTablaCliente() throws MiException {
		return clienteDao.crearTablaCliente();
	}

	public boolean eliminarCliente(Cliente cliente) throws MiException {
		return clienteDao.eliminarCliente(cliente);
	}

	public boolean insertarCliente(int clienteID, String nombre, String auto,String mail,String patente) throws MiException {
		return clienteDao.insertarCliente(clienteID, nombre, auto, mail, patente);
	}

	public boolean insertarCliente(Cliente cliente) throws MiException {
		return clienteDao.insertarCliente(cliente);
	}

	public boolean updateCliente(Cliente cliente) throws MiException {
		return clienteDao.updateCliente(cliente);
	}

	public Cliente buscarClientePorApodo(String userToFind) throws MiException {
		return clienteDao.buscarClientePorApodo(userToFind);
	}

	public int buscarUltimoClienteId() throws MiException {
		return clienteDao.buscarUltimoClienteId();
	}
	
}
