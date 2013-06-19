package handler;

import java.util.List;

import javax.swing.JPanel;

import utils.MiException;

import entities.Aceite;
import entities.Autoparte;
import entities.Cliente;
import entities.Filtro;
import entities.Lampara;
import entities.Usuario;

import bo.AceiteBO;
import bo.AutoparteBO;
import bo.ClienteBO;
import bo.FiltroBO;
import bo.GeneralBO;
import bo.LamparaBO;
import bo.ReparacionBO;
import bo.UsuarioBO;
import main.MiFrame;

public class Handler {

	private MiFrame frame;
	private UsuarioBO usuarioBO;
	private ClienteBO clienteBO;
	private AutoparteBO autoparteBO;
	private ReparacionBO reparacionBO;
	private GeneralBO generalBO;
	private FiltroBO filtroBO;
	private AceiteBO aceiteBO;
	private LamparaBO lamparaBO;
	
	
	public Handler() {
		this.usuarioBO = new UsuarioBO();
		this.clienteBO=new ClienteBO();
		this.autoparteBO=new AutoparteBO();
		this.reparacionBO=new ReparacionBO();
		this.generalBO=new GeneralBO();
		this.filtroBO=new FiltroBO();
		this.aceiteBO=new AceiteBO();
		this.lamparaBO=new LamparaBO();
		
	}
	
	public void setFrame(MiFrame frame) {
		this.frame = frame;
	}
	public MiFrame getFrame()
	{
		return this.frame;
	}
	
	public void backToPrincipal() {
		frame.switchPanel(new JPanel());
	}
	
	public void eliminarUsuario(Usuario usuario) throws MiException {
		usuarioBO.eliminarUsuario(usuario);		
	}

	public List<Usuario> cargarUsuarios() throws MiException {
		return usuarioBO.cargarUsuarios();
	}

	public Usuario buscarUsuarioPorApodo(String userName) throws MiException {
		return usuarioBO.buscarUsuarioPorApodo(userName);
	}

	public int buscarUltimoUsuarioId() throws MiException{
		
		return usuarioBO.buscarUltimoUsuarioId();
	}

	public boolean updateUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.updateUsuario(usuario);
	}

	public boolean insertarUsuario(Usuario usuario) throws MiException {
		
		return usuarioBO.insertarUsuario(usuario);
	}

	public int buscarUltimoClienteId() throws MiException {
		return clienteBO.buscarUltimoClienteId();
	}

	public boolean insertarCliente(Cliente cliente) throws MiException {

		return clienteBO.insertarCliente(cliente);
	}

	public boolean eliminarCliente(Cliente cliente) throws MiException {
		
		return clienteBO.eliminarCliente(cliente);
	}

	public List<Cliente> cargaClientes() throws MiException {
		return clienteBO.cargaClientes();
	}

	public Cliente buscarClientePorApodo(String text) throws MiException {
		return clienteBO.buscarClientePorApodo(text);
	}

	public boolean updateCliente(Cliente cliente) throws MiException {
		return clienteBO.updateCliente(cliente);
	}
	
	public int buscarUltimoFiltroId() throws MiException {
		return filtroBO.buscarUltimoFiltroId();
	}

	public boolean insertarFiltro(Filtro filtro) throws MiException {
		return filtroBO.insertarFiltro(filtro);
	}
	public boolean eliminarFiltro(Filtro filtro) throws MiException {
		return filtroBO.eliminarFiltro(filtro);
	}

	public int buscarUltimaAutoparteId() throws MiException {
		return autoparteBO.buscarUltimaAutoparteId();
	}

	public List<Autoparte> cargaAutopartes() throws MiException {
		
		return autoparteBO.cargaAutopartes();
	}
	public boolean insertarAutoparte(Autoparte autoparte) throws MiException {
		return autoparteBO.insertarAutoparte(autoparte);
	}

	public boolean eliminarAutoparte(Autoparte autoparte) throws MiException {
		return autoparteBO.eliminarAutoparte(autoparte);
	}
	public Autoparte buscarAutopartePorId(int id) throws MiException{
		return autoparteBO.buscarAutopartePorId(id);
	}
	public Filtro buscarFiltroPorIdAutoParte(int id) throws MiException {
		return filtroBO.buscarFiltroPorIdAutoParte(id);
	}

	public boolean insertarAceite(Aceite aceite) throws MiException {
		return aceiteBO.insertarAceite(aceite);
	}

	public int buscarUltimoAceiteId() throws MiException {
		return aceiteBO.buscarUltimoAceiteId();
	}

	public Aceite buscarAceitePorIdAutoParte(int id) throws MiException {
		return aceiteBO.buscarAceitePorIdAutoParte(id);
	}

	public boolean eliminarAceite(Aceite aceite) throws MiException {
		return aceiteBO.eliminarAceite(aceite);
	}

	public int buscarUltimoLamparaId() throws MiException {
		return lamparaBO.buscarUltimoLamparaId();
	}

	public boolean insertarLampara(Lampara lampara) throws MiException {
		return lamparaBO.insertarLampara(lampara);
	}

	public Lampara buscarLamparaPorIdAutoParte(int id) throws MiException {
		return lamparaBO.buscarLamparaPorIdAutoParte(id);
	}

	public boolean eliminarLampara(Lampara lampara) throws MiException {
		return lamparaBO.eliminarLampara(lampara);
	}

	


}
