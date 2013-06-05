package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import paneles.Panel;

import bo.AceiteBO;
import bo.AutoparteBO;
import bo.ClienteBO;
import bo.FiltroBO;
import bo.LamparaBO;
import bo.ReparacionBO;
import bo.UsuarioBO;

import main.Menu.MenuAdministrador;
import main.Menu.MenuSistema;
import utils.Definiciones;
import utils.MetodosGrl;
import utils.MiException;

import entities.Autoparte;
import entities.Cliente;
import entities.Reparacion;
import entities.Usuario;

public class Swing {

	private JFrame frameSwing;
	private JMenuBar menuBar;
	

	private JPanel panelInicio;
	private List<Autoparte> autopartesG;
	private List<Cliente> clientesG;
	private List<Reparacion> reparacionesG;
	private List<Usuario> usuariosG;
	private MetodosGrl metgral;
	private Reparacion reparacionAux;
	public Swing()
	{
		reparacionAux=new Reparacion();
		autopartesG=new ArrayList<Autoparte>();
		clientesG=new ArrayList<Cliente>();
		reparacionesG=new ArrayList<Reparacion>();		
		usuariosG=new ArrayList<Usuario>();
		this.setPanelInicio(new JPanel(new BorderLayout()));
		this.setMenuBar(new JMenuBar());
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	public List<Usuario> getUsuariosG() {
		return usuariosG;
	}
	public void setUsuariosG(List<Usuario> usuariosG) {
		this.usuariosG = usuariosG;
	}
	public List<Autoparte> getAutopartesG() {
		return autopartesG;
	}
	public void setAutopartesG(List<Autoparte> autopartesG) {
		this.autopartesG = autopartesG;
	}
	public List<Cliente> getClientesG() {
		return clientesG;
	}
	public void setClientesG(List<Cliente> clientesG) {
		this.clientesG = clientesG;
	}
	public List<Reparacion> getReparacionesG() {
		return reparacionesG;
	}
	public void setReparacionesG(List<Reparacion> reparacionesG) {
		this.reparacionesG = reparacionesG;
	}
	public JPanel getPanelInicio() {
		return panelInicio;
	}
	public void setPanelInicio(JPanel panelInicio) {
		this.panelInicio = panelInicio;
	}
	public JFrame getFrameSwing() {
		return frameSwing;
	}
	public void setFrameSwing(JFrame frameSwing) {
		this.frameSwing = frameSwing;
	}
	
	public Reparacion getReparacionAux() {
		return reparacionAux;
	}
	public void setReparacionAux(Reparacion reparacionAux) {
		this.reparacionAux = reparacionAux;
	}
	public void pantallaPrincipal(JFrame frame)
	{
		final Panel panelGestor=new Panel();
		final UsuarioBO usuarioBo=new UsuarioBO();
		final ClienteBO clienteBo=new ClienteBO();		
		final AutoparteBO autoparteBo=new AutoparteBO();
		final ReparacionBO reparacionBo=new ReparacionBO();
		metgral=new MetodosGrl(); 
		
		///######################## CARGA DE ENTIDADES ######################
		try
		{
			System.out.print("\n\n***CARGA DE ENTIDADES***\n");
			this.setUsuariosG(usuarioBo.cargarUsuarios());
			this.setClientesG(clienteBo.cargaClientes());
			this.setReparacionesG(reparacionBo.cargaReparaciones());
			this.setAutopartesG(autoparteBo.cargaAutopartes());
		}
		catch(MiException e)
		{
			throw  e;
		}catch(Exception e)
		{
			throw new MiException("[MENU] Error al cargar: "+e);
		}
		//#########################################################################
		
		this.setFrameSwing(frame);		
		this.getFrameSwing().getContentPane().removeAll();
		this.getFrameSwing().getContentPane().repaint();
		setMenuBar(new JMenuBar());
		getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
		
		
		
		//####################################### USUARIO ##################################
		JMenu menuUsuario=new JMenu("Usuario");
		JMenuItem itemCrearUsuario=new JMenuItem("Crear Usuario");
		JMenuItem itemModificarUsuario=new JMenuItem("Modificar Usuario");
		JMenuItem itemBajaUsuario=new JMenuItem("Baja Usuario");
		JMenuItem itemMostrarUsuario=new JMenuItem("Mostrar Usuarios");
		
		//////////CREAR USUARIO /////////////
		itemCrearUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setFrameSwing(getFrameSwing());		
				getFrameSwing().getContentPane().removeAll();
				getFrameSwing().getContentPane().repaint();
				getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
				
				JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,400),null);

				final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
				final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
				final JTextField areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
				final JTextField areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
				final JTextField areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
				
				JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				JLabel labelUserName=panelGestor.crearLabel("UserName",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				JLabel labelPassword=panelGestor.crearLabel("Password",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);

				JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
				JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
				
				JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					
				
				JButton btSubmit=new JButton();
				btSubmit.setText("SUBMIT");								
				btSubmit.addMouseListener(new MouseAdapter() {												
	            @Override
	            public void mouseReleased(MouseEvent evt) {
	            	Usuario userCreate=new Usuario();
	            	try {					            		
	            		if(areaName.getText().equals("")||areaPassword.getText().equals("")||areaUserName.getText().equals(""))
	            		{
	            			System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
	            			Thread.sleep(2000);
	            			pantallaPrincipal(getFrameSwing());
	            			
	            		}else
	            		{	
	            			userCreate.setName(areaName.getText());							            	
	    					userCreate.setEmail(areaEmail.getText());							
	    					userCreate.setUsername(areaUserName.getText());							
	    					userCreate.setPassword(areaPassword.getText());							
	    					userCreate.setJerarquia(areaJerarquia.getText());
	    					userCreate.setLogueado(0);
	    					userCreate.setId(usuarioBo.buscarUltimoUsuarioId());			//podria buscar en la lista, pero ...
	    					
							if(usuarioBo.insertarUsuario(userCreate))
							{
								System.out.print("\nUSUARIO CREADO CORRECTAMENTE");
								Thread.sleep(2000);							
								getUsuariosG().add(userCreate);
								
							}else
							{
								System.out.print("\nFALLO CREACION USUARIO");
								Thread.sleep(2000);
							}
							pantallaPrincipal(getFrameSwing());
	            		}
					} catch (MiException e) {
							throw e;					
		            } catch (Exception e) {
						throw new MiException("[CREATE USER]EXCEPTION : "+e);												
					}
	            }
			});

			panel.add(BorderLayout.NORTH,panelTitulo);
			panel.add("Center",panelGrid);
			panel.add("South",panelResto);
			getPanelInicio().add(panel,BorderLayout.CENTER);
			getPanelInicio().add(btSubmit,BorderLayout.SOUTH);
		
			getFrameSwing().setContentPane(getPanelInicio());
								
				
			}
		});
		////////////// MODIFICAR USUARIO ////////////////////
		itemModificarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try{
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					String seleccion=null;
					seleccion = JOptionPane.showInputDialog(getFrameSwing(),"","INGRESE USUARIO A MODIFICAR",JOptionPane.QUESTION_MESSAGE);  
					
					
					if((seleccion==null)||(seleccion.equals("")))
	            	{
	            		System.out.print("\n[SUBMIT MODIFICAR] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
	            		Thread.sleep(2000);
	            		pantallaPrincipal(getFrameSwing());
	            	}else
	            	{
	            		final Usuario usuarioModif=metgral.buscarUsuarioPorApodo(seleccion,getUsuariosG());
	            		if(usuarioModif==null)
						{
							System.out.print("\nNO SE ENCONTRO EL USUARIO");
							Thread.sleep(2000);
							pantallaPrincipal(getFrameSwing());
						}else
						{
							JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
							JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,400),null);
							
							final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaUserName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaPassword=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaJerarquia=panelGestor.crearTextField("",30,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
							
							JLabel labelName=panelGestor.crearLabel("Nombre ("+usuarioModif.getName()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelEmail=panelGestor.crearLabel("Email ("+usuarioModif.getEmail()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelUserName=panelGestor.crearLabel("UserName ("+usuarioModif.getUsername()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelPassword=panelGestor.crearLabel("Password ("+usuarioModif.getPassword()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelJerarquia=panelGestor.crearLabel("Jerarquia ("+usuarioModif.getJerarquia()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.LEFT_ALIGNMENT,null,Definiciones.line_blackline);
	
							JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaUserName,areaPassword,areaJerarquia};
							JLabel[] labels=new JLabel[]{labelName,labelEmail,labelUserName,labelPassword,labelJerarquia};
							
							JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
						
		    				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE USUARIO",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
							
							final JButton btSubmit=new JButton();
							btSubmit.setText("SUBMIT");								
							btSubmit.addMouseListener(new MouseAdapter() {												
								@Override
								public void mouseReleased(MouseEvent evt) {
									try
									{
										usuarioModif.setName(areaName.getText());							            	
										usuarioModif.setEmail(areaEmail.getText());							
										usuarioModif.setUsername(areaUserName.getText());							
										usuarioModif.setPassword(areaPassword.getText());							
										usuarioModif.setJerarquia(areaJerarquia.getText());
										usuarioModif.setLogueado(0);									
										
										if(usuarioBo.updateUsuario(usuarioModif))
										{
											System.out.print("\nUSUARIO MODIFICADO CORRECTAMENTE");
											Thread.sleep(2000);
											int i=0;
											for(Usuario user:getUsuariosG())
											{
												if(usuarioModif.getName().equals(user.getName()))
												{
													break;
												}
												i++;
											}
											getUsuariosG().remove(i);								
											//usuarios=metgral.eliminarUsuarioDeLista(usuarios,usuarioModif);										
											getUsuariosG().add(usuarioModif);
											
											pantallaPrincipal(getFrameSwing());										
										}else
										{
											System.out.print("\nFALLO MODIFICACION USUARIO");
											Thread.sleep(2000);
											pantallaPrincipal(getFrameSwing());
										}
									}catch(MiException e)
									{
										throw e;
									}catch(Exception e)
									{
										throw new MiException("[MODIFICAR USER] EXCEPTION : "+e);
									}
				            	
				            	}
							});
							
							panel.add(BorderLayout.NORTH,panelTitulo);
							panel.add(BorderLayout.CENTER,panelGrid);
							panel.add(BorderLayout.SOUTH,panelResto);
							getPanelInicio().add(panel,BorderLayout.CENTER);
							getPanelInicio().add(btSubmit,BorderLayout.SOUTH);
							
							getFrameSwing().setContentPane(getPanelInicio());
						}
					
					}
				} catch (MiException e) {
					throw e;					
	            } catch (Exception e) {
					throw new MiException("[CREATE USER]EXCEPTION : "+e);												
				}
			}
			});
		
		////////////////// BAJA USUARIO /////////////////////////
		itemBajaUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				setFrameSwing(getFrameSwing());		
				getFrameSwing().getContentPane().removeAll();
				getFrameSwing().getContentPane().repaint();
				getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
				
				JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,400),null);
				
				final JTextField tfUsuario=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);
				JLabel lUsuario=panelGestor.crearLabel("Ingrese USER NAME de usuario a eliminar",new Font(Font.SERIF,Font.BOLD,12),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				
				JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(new JTextField[]{tfUsuario},new JLabel[]{lUsuario});
						
					JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	try
		            	{
			            	if(tfUsuario.getText().equals(""))
			            	{
			            		System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
		            			Thread.sleep(2000);
		            			pantallaPrincipal(getFrameSwing());
			            	}else
			            	{
			            		Usuario usuarioBaja=metgral.buscarUsuarioPorApodo(tfUsuario.getText(),getUsuariosG());
			            		if(usuarioBaja==null)
								{
			            			System.out.print("\nNO SE ENCONTRO USER");				//DESPUES HACER CUADROS INFORMATIVOS
			            			Thread.sleep(2000);
			            			pantallaPrincipal(getFrameSwing());
			            		}
			            		
			            		JOptionPane optionPane= new JOptionPane();
			            		int response=JOptionPane.showConfirmDialog(getFrameSwing(), "ELIMINAR USUARIO "+tfUsuario.getText()+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			            		
			            		if(optionPane.YES_OPTION==response)
			            		{	
				            		if(usuarioBo.eliminarUsuario(usuarioBaja))
									{
										System.out.print("\nUSUARIO ELIMINADO CORRECTAMENTE");		//DEBUG AGREGAR VENTANA EMERG.
										Thread.sleep(2000);
										int i=0;
										for(Usuario user:getUsuariosG())
										{
											if(usuarioBaja.getName().equals(user.getName()))
											{
												break;
											}
											i++;
										}
										getUsuariosG().remove(i);			            			
				            			pantallaPrincipal(getFrameSwing());
									}else
									{
										System.out.print("\nFALLO BAJA USUARIO");																		
				            			Thread.sleep(2000);
				            			pantallaPrincipal(getFrameSwing());
									}
			            		}else if(optionPane.NO_OPTION==response)
			            		{	
			            			pantallaPrincipal(getFrameSwing());
			            		}else
			            		{
			            			System.out.print("\nNO SE ENCONTRO USER");				//DESPUES HACER CUADROS INFORMATIVOS
			            			Thread.sleep(2000);
			            			pantallaPrincipal(getFrameSwing());
			            		}
			            	}
		            	}
		            	catch(MiException e)
		            	{
		            		throw e;
		            	}catch(Exception e)
		            	{
		            		throw new MiException("[BAJA USUARIO] MI EXCEPTION : "+e);
		            	} 
		            }
					});
					
					panel.add(panelGrid,BorderLayout.NORTH);
					panel.add(panelResto,BorderLayout.CENTER);
					panel.add(btSubmit,BorderLayout.SOUTH);
					getPanelInicio().add(panel,BorderLayout.CENTER);
					
					getFrameSwing().setContentPane(getPanelInicio());
		
			}
			});
		
		itemMostrarUsuario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			});
		
		menuUsuario.add(itemCrearUsuario);		
		menuUsuario.add(itemModificarUsuario);		
		menuUsuario.add(itemBajaUsuario);
		menuUsuario.add(itemMostrarUsuario);
		
		//#################################3 CLIENTE ###########################################
		JMenu menuCliente=new JMenu("Cliente");
		JMenuItem itemCrearCliente=new JMenuItem("Crear Cliente");
		JMenuItem itemModificarCliente=new JMenuItem("Modificar Cliente");
		JMenuItem itemBajaCliente=new JMenuItem("Baja Cliente");
		JMenuItem itemMostrarClientes=new JMenuItem("Mostrar Clientes");
		
		////////////////// CREAR CLIENTE //////////////////
		itemCrearCliente.addActionListener(new ActionListener(){
						@Override
			public void actionPerformed(ActionEvent arg0) {
				
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
					JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,400),null);

					final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					final JTextField areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
					
					JLabel labelName=panelGestor.crearLabel("Nombre",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelEmail=panelGestor.crearLabel("Email",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
					JLabel labelAuto=panelGestor.crearLabel("Auto",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
				
					JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto};
					JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto};
					
					JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
					JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"CREACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
						
					JButton btSubmit=new JButton();
					btSubmit.setText("SUBMIT");								
					btSubmit.addMouseListener(new MouseAdapter() {												
		            @Override
		            public void mouseReleased(MouseEvent evt) {
		            	Usuario userCreate=new Usuario();
		            	try {					            		
		            		if(areaName.getText().equals("")||areaEmail.getText().equals("")||areaAuto.getText().equals(""))
		            		{
		            			System.out.print("\nDATOS VACIOS");				//DESPUES HACER CUADROS INFORMATIVOS
		            			Thread.sleep(2000);
		            			pantallaPrincipal(getFrameSwing());
		            			
		            		}else
		            		{	
		            			Cliente cliente=new Cliente();
		            			
		            			cliente.setNombre(areaName.getText());							            	
		            			cliente.setMail(areaEmail.getText());							
		            			cliente.setAuto(areaAuto.getText());
		    					cliente.setId(clienteBo.buscarUltimoClienteId());			//podria buscar en la lista, pero ...
		    					
								if(clienteBo.insertarCliente(cliente))
								{
									getClientesG().add(cliente);
									System.out.print("\nCLIENTE CREADO CORRECTAMENTE");
									Thread.sleep(2000);							
									
									
								}else
								{
									System.out.print("\nFALLO CREACION CLIENTE");
									Thread.sleep(2000);
								}
								pantallaPrincipal(getFrameSwing());
		            		}
						} catch (MiException e) {
								throw e;					
			            } catch (Exception e) {
							throw new MiException("[CREATE CLIENTE]EXCEPTION : "+e);												
						}
		            }
				});
				panel.add(BorderLayout.NORTH,panelTitulo);	
				panel.add("Center",panelGrid);
				panel.add("South",panelResto);
				getPanelInicio().add(panel,BorderLayout.CENTER);
				getPanelInicio().add(btSubmit,BorderLayout.SOUTH);
			
				getFrameSwing().setContentPane(getPanelInicio());
			}
		});
		//////////////// MODIFICACION CLIENTE ///////////////////
		itemModificarCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					String seleccion=null;
					seleccion = JOptionPane.showInputDialog(getFrameSwing(),"","Ingrese Cliente a modificar",JOptionPane.QUESTION_MESSAGE);  
					
					if((seleccion==null)||(seleccion.equals("")))
	            	{
	            		System.out.print("\n[MODIFICAR CLIENTE] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
	            		Thread.sleep(2000);
	            		pantallaPrincipal(getFrameSwing());
	            	}else
	            	{
	            		final Cliente clienteEncontrado=metgral.buscarClientePorApodo(seleccion,clientesG);
						if(clienteEncontrado==null)
						{
	            			System.out.print("\nNO SE ENCONTRO CLIENTE");				//DESPUES HACER CUADROS INFORMATIVOS
	            			Thread.sleep(2000);
	            			pantallaPrincipal(getFrameSwing());
	            		}
						else
						{
							JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
							JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,400),null);

							final JTextField areaName=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaEmail=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							final JTextField areaAuto=panelGestor.crearTextField("",20,Definiciones.line_blackline,new Font(Font.SERIF,-1,12),Color.white,JTextField.LEFT_ALIGNMENT);				
							
							JLabel labelName=panelGestor.crearLabel("Nombre ("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelEmail=panelGestor.crearLabel("Email("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
							JLabel labelAuto=panelGestor.crearLabel("Auto("+clienteEncontrado.getNombre()+")",new Font(Font.SERIF,Font.BOLD,14),JLabel.RIGHT_ALIGNMENT,null,Definiciones.line_blackline);
						
							JTextField[] textFields=new JTextField[]{areaName,areaEmail,areaAuto};
							JLabel[] labels=new JLabel[]{labelName,labelEmail,labelAuto};
							
							JPanel panelGrid=panelGestor.crearPanelConFieldsEnForm(textFields,labels);
							JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MODIFICACION DE CLIENTE",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
							
							final JButton btSubmit=new JButton();
							btSubmit.setText("SUBMIT");								
							btSubmit.addMouseListener(new MouseAdapter() {												
								@Override
								public void mouseReleased(MouseEvent evt) {
									try
									{
										if(areaName.getText().equals("")&&areaEmail.getText().equals("")&&areaAuto.getText().equals(""))
					            		{	
					            				System.out.print("\n\nDATOS VACIOS - FALLO MODIFICACION CLIENTE");	//AGREGAR AVISO EMERGENTE
					            				Thread.sleep(2000);
						            			pantallaPrincipal(getFrameSwing());
					            		}else
					            		{
					            			clienteEncontrado.setNombre(areaName.getText());							            	
					            			clienteEncontrado.setMail(areaEmail.getText());							
					            			clienteEncontrado.setAuto(areaAuto.getText());
					            			clienteEncontrado.setId(clienteBo.buscarUltimoClienteId());		
				    											
											if(clienteBo.updateCliente(clienteEncontrado))
											{	
												setClientesG(metgral.eliminarClienteDeLista(getClientesG(),clienteEncontrado));
												getClientesG().add(clienteEncontrado);
												System.out.print("\nCLIENTE MODIFICADO CORRECTAMENTE");
												Thread.sleep(2000);
																								
											}else
											{
												System.out.print("\nFALLO MODIFICACION CLIENTE");
												Thread.sleep(2000);												
											}								
											pantallaPrincipal(getFrameSwing());
					            		}										
									}catch(MiException e)
									{
										throw e;
									}catch(Exception e)
									{
										throw new MiException("[MODIFICAR USER] EXCEPTION : "+e);
									}
				            	
				            	}
							});
							
							panel.add(BorderLayout.NORTH,panelTitulo);
							panel.add(BorderLayout.CENTER,panelGrid);
							panel.add(BorderLayout.SOUTH,panelResto);
							
							getPanelInicio().add(panel,BorderLayout.CENTER);
							getPanelInicio().add(btSubmit,BorderLayout.SOUTH);							
							getFrameSwing().setContentPane(getPanelInicio());
							
						}
	            	}	
				} catch (MiException e) {
					throw e;					
		        } catch (Exception e) {
					throw new MiException("[MODIFICAR CLIENTE]EXCEPTION : "+e);												
				}
				
			}
			});
		///////////// BAJA CLIENTE ////////////////////////
		itemBajaCliente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					String seleccion=null;
					seleccion = JOptionPane.showInputDialog(getFrameSwing(),"","Ingrese Cliente a eliminar",JOptionPane.QUESTION_MESSAGE);  
					
					if((seleccion==null)||(seleccion.equals("")))
	            	{
	            		System.out.print("\n[BAJA CLIENTE] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
	            		Thread.sleep(2000);
	            		pantallaPrincipal(getFrameSwing());
	            	}else
	            	{
	            		final Cliente clienteEncontrado=metgral.buscarClientePorApodo(seleccion,clientesG);
						if(clienteEncontrado==null)
						{
	            			System.out.print("\nNO SE ENCONTRO CLIENTE");				//DESPUES HACER CUADROS INFORMATIVOS
	            			Thread.sleep(2000);
	            			pantallaPrincipal(getFrameSwing());
	            		}
	            		
	            		JOptionPane optionPane= new JOptionPane();
	            		int response=JOptionPane.showConfirmDialog(getFrameSwing(), "ELIMINAR CLIENTE "+seleccion+"?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	            		
	            		if(optionPane.YES_OPTION==response)
	            		{	
	            			if(clienteBo.eliminarCliente(clienteEncontrado))
							{
								System.out.print("\nCLIENTE ELIMINADO CORRECTAMENTE");		//DEBUG
								Thread.sleep(2000);
								clientesG=metgral.eliminarClienteDeLista(getClientesG(),clienteEncontrado);
								
							}else
							{
								System.out.print("\nFALLO BAJA CLIENTE");		//DEBUG
								Thread.sleep(2000);
								
							}
	            			pantallaPrincipal(getFrameSwing());
	            			
	            		}else if(optionPane.NO_OPTION==response)
	            		{	
	            			pantallaPrincipal(getFrameSwing());
	            		}else
	            		{	
	            			pantallaPrincipal(getFrameSwing());
	            		}
					}	
				} catch (MiException e) {
					throw e;					
		        } catch (Exception e) {
					throw new MiException("[BAJA CLIENTE]EXCEPTION : "+e);												
				}
			}
			});
		//////////////// MOSTRAR CLIENTES /////////////////
		itemMostrarClientes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
            	{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
					JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.white,new Dimension(400,400),null);
					JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR CLIENTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);
					 			     
			        //JPanel panelGrid=panelGestor.cargarClientesEnPanel(getClientesG());
			    	JTable table=panelGestor.cargarClientesEnTabla(getClientesG());										
					
			        
			        panel.add(BorderLayout.NORTH,panelTitulo);
					panel.add(BorderLayout.CENTER,table);
					panel.add(BorderLayout.SOUTH,panelResto);
					getPanelInicio().add(panel,BorderLayout.CENTER);					
					
					getFrameSwing().setContentPane(getPanelInicio());
					
            	}catch(MiException e)
				{
					throw e;
				}catch(Exception e)
				{
					throw new MiException("\n[MOSTRAR CLIENTES]MOSTRAR DATOS CLIENTE Exception : "+e);
				}
			}
			});
		
		menuCliente.add(itemCrearCliente);		
		menuCliente.add(itemModificarCliente);		
		menuCliente.add(itemBajaCliente);
		menuCliente.add(itemMostrarClientes);
		
		//#################################3 AUTOPARTE ###########################################
		JMenu menuAutoparte=new JMenu("Autoparte");									//...
		JMenu menuFiltro=new JMenu("Filtro");
		JMenu menuAceite=new JMenu("Aceite");
		JMenu menuLampara=new JMenu("Lampara");
		
		JMenuItem itemCrearFiltro=new JMenuItem("Crear Filtro");
		JMenuItem itemModificarFiltro=new JMenuItem("Modificar Filtro");
		JMenuItem itemBajaFiltro=new JMenuItem("Baja Filtro");
		JMenuItem itemCrearAceite=new JMenuItem("Crear Aceite");
		JMenuItem itemModificarAceite=new JMenuItem("Modificar Aceite");
		JMenuItem itemBajaAceite=new JMenuItem("Baja Aceite");
		JMenuItem itemCrearLampara=new JMenuItem("Crear Aceite");
		JMenuItem itemModificarLampara=new JMenuItem("Modificar Aceite");
		JMenuItem itemBajaLampara=new JMenuItem("Baja Aceite");
		
		JMenuItem itemMostrarAutopartes=new JMenuItem("Mostrar Aceite");
		////////
		/////////////// CREAR FILTRO /////////////////
		itemCrearFiltro.addActionListener(new ActionListener(){
						@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		/////////////// MODIFICAR FILTRO /////////////////
		itemModificarFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			});
		
		/////////////////// ELIMINAR FILTRO //////////////
		itemBajaFiltro.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			});
		/////////////////////////
		menuFiltro.add(itemCrearFiltro);		
		menuFiltro.add(itemModificarFiltro);
		menuFiltro.add(itemBajaFiltro);
		
		////////////// ///// CREAR ACEITE /////////////
		itemCrearAceite.addActionListener(new ActionListener(){
			@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		
		////////////////// MODIFICAR ACEITE /////////////////
		itemModificarAceite.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		
		//////////////// ELIMINAR ACEITE ////////////
		itemBajaAceite.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		
		menuAceite.add(itemCrearAceite);		
		menuAceite.add(itemModificarAceite);
		menuAceite.add(itemBajaAceite);
		///////////////
		//////////////////// CREAR LAMPARA ///////////////
		itemCrearLampara.addActionListener(new ActionListener(){
			@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		
		/////////////// MODIFICAR LAMPARA ///////////////
		itemModificarLampara.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		///////////// ELIMINAR LAMPARA ///////////////
		itemBajaLampara.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		});
		/////////////
		itemMostrarAutopartes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				getFrameSwing().getContentPane().add(new JLabel("Drag to move", JLabel.CENTER),BorderLayout.CENTER);
				getFrameSwing().setVisible(true);
			}
			});
		////////
		menuLampara.add(itemCrearLampara);		
		menuLampara.add(itemModificarLampara);
		menuLampara.add(itemBajaLampara);
		
		menuAutoparte.add(menuFiltro);
		menuAutoparte.add(menuAceite);
		menuAutoparte.add(menuLampara);
		
		menuAutoparte.add(itemMostrarAutopartes);
		//################################# REPARACION ###########################################
		JMenu menuReparacion=new JMenu("Reparacion");									//...
		
		JMenuItem itemIniciarReparacion=new JMenuItem("Iniciar Reparacion");
		JMenuItem itemFinalizarReparacion=new JMenuItem("Finalizar Reparacion");
		JMenuItem itemReparaPendientes=new JMenuItem("Ver Pendientes");
		JMenuItem itemReparaFinalizadas=new JMenuItem("Ver Finalizadas");

		//////////// INICIAR REPARACION /////////////////
		itemIniciarReparacion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					String seleccion=null;
					seleccion = JOptionPane.showInputDialog(getFrameSwing(),"","Ingrese Cliente para nueva reparacion",JOptionPane.QUESTION_MESSAGE);  
					
					if((seleccion==null)||(seleccion.equals("")))
	            	{
	            		System.out.print("\n[INICIAR REPARACION] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
	            		Thread.sleep(2000);
	            		pantallaPrincipal(getFrameSwing());
	            	}else
	            	{
	            		final Cliente clientereparacion=metgral.buscarClientePorApodo(seleccion,getClientesG());
						if(clientereparacion==null)
						{
							System.out.print("\n\nCLIENTE NO ENCONTRADO\n");		//DEBUG AGREGAR EMERGENTE								
							pantallaPrincipal(getFrameSwing());			
						}else
						{	
							Calendar c = new GregorianCalendar();
						    Date d1 = c.getTime(); 
							int response=JOptionPane.showConfirmDialog(getFrameSwing(), "CARGA DE REPARACION 'Cliente: "+clientereparacion.getNombre()+"' - 'Auto: "+clientereparacion.getAuto(), "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							    
							if(response==JOptionPane.YES_OPTION)
							{
								Reparacion nuevareparacion=new Reparacion();
								nuevareparacion.setFechainicio(d1.toString());			
								nuevareparacion.setCliente(clientereparacion);
								nuevareparacion.setEntregado(0);
								
								nuevareparacion.setId(reparacionBo.buscarUltimaReparacionId());
								if(reparacionBo.insertarReparacionInicio(nuevareparacion))
								{
									getReparacionesG().add(nuevareparacion);
									
									System.out.print("\nCARGA DE NUEVA REPARACION CORRECTA\n");
									 
									JOptionPane.showMessageDialog(getFrameSwing(), "CARGA DE NUEVA REPARACION\n"+d1.toString());
									 
								}else{
									JOptionPane.showMessageDialog(getFrameSwing(), "FALLO CARGA");
								}							
							}else
							{	
								JOptionPane.showMessageDialog(getFrameSwing(), "CARGA CANCELADA");
							}
							pantallaPrincipal(getFrameSwing());
						}
	        		}	
				} catch (MiException e) {
					throw e;					
		        } catch (Exception e) {
					throw new MiException("[INICIAR REPARACION]EXCEPTION : "+e);												
				}
				            	
			}
		});
		
		////////////// FINALIZAR REPARACION /////////////
		itemFinalizarReparacion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					String seleccion=null;
					seleccion = JOptionPane.showInputDialog(getFrameSwing(),"","Ingrese Cliente Reparacion Iniciada",JOptionPane.QUESTION_MESSAGE);	//PODRIA BUSCAR POR ID REPARACION PARA KE UN CLIENTE TENGA MAS DE UNA REPARACION  
					
					if((seleccion==null)||(seleccion.equals("")))
	            	{
	            		System.out.print("\n[INICIAR REPARACION] DATOS VACIOS");		//DEBUG AGREGAR VENTANA EMERG.
	            		Thread.sleep(2000);
	            		pantallaPrincipal(getFrameSwing());
	            	}else
	            	{
	            		final Cliente cliente=metgral.buscarClientePorApodo(seleccion,getClientesG());	//PODRIA BUSCAR POR ID REPARACION
						if(cliente==null)
						{
							System.out.print("\n\nCLIENTE NO ENCONTRADO\n");		//DEBUG AGREGAR EMERGENTE								
							
							pantallaPrincipal(getFrameSwing());			
						}else
						{	
							for(Reparacion reparaL:getReparacionesG())
							{
								if(reparaL.getCliente().getId()==cliente.getId())
								{	
									if(reparaL.getEntregado()==1)												
									{	
										JOptionPane.showMessageDialog(getFrameSwing(), "CLIENTE NO POSEE NINGUNA REPARACION ABIERTA");
										pantallaPrincipal(getFrameSwing());										
										break;
									}
									else
									{	
										JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
										JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.gray,new Dimension(400,200),null);
										JPanel panelOpcion=panelGestor.crearPanelGrid(new GridLayout(2,1),null,Color.gray,new Dimension(400,80),null);
										final JTextField fieldOpcion=new JTextField();
										
										setReparacionAux(new Reparacion());
										setReparacionAux(reparaL);
										Calendar c = new GregorianCalendar();
									    Date d1 = c.getTime();
									    getReparacionAux().setFechaentrega(d1.toString());
									    getReparacionAux().setEntregado(1);
										//getReparacionAux().setCliente(cliente);
										
										JPanel panelAutopart= panelGestor.cargarAutopartesEnPanel(getAutopartesG());
										panelAutopart.add(panelGestor.crearPanelOpcion(new String("INGRESE ID AUTOPARTE"),fieldOpcion));
										
										panel.add(BorderLayout.NORTH,panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"AUTOPARTES USADAS EN LA REPARACION",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,11),Color.white));										
										panel.add(BorderLayout.CENTER,panelAutopart);
										panel.add(BorderLayout.SOUTH,panelResto);
										
										
										///################# AGREGAR ##########################
										JButton btAdd=new JButton();
										btAdd.setText("AGREGAR");								
										btAdd.addMouseListener(new MouseAdapter() {												
							            @Override
							            public void mouseReleased(MouseEvent evt) {
							            	
							            	try
							            	{
							            		if(fieldOpcion.getText().equals("")||fieldOpcion==null)	
							            		{
							            			JOptionPane.showMessageDialog(getFrameSwing(), "NO SE COLOCO NINGUNA OPCION");
								            		//pantallaPrincipal(getFrameSwing());					            		
								            	}else
								            	{	
							            			for (Autoparte autoparte : getAutopartesG())
													{										
														if(autoparte.getId()==Integer.valueOf(fieldOpcion.getText()))
														{	
															if(getReparacionAux().getCosto()!=0)
															{
																getReparacionAux().setCosto(getReparacionAux().getCosto()+autoparte.getCosto());
															}
															else
															{
																getReparacionAux().setCosto(autoparte.getCosto());
															}
															
															getReparacionAux().getAutopartes().add(autoparte);
															
															System.out.print("\n REPARACIOES AUTOPART: "+getReparacionAux().getAutopartes().size());
															fieldOpcion.setText("");
															break;
														}
													}
													
							            			JOptionPane.showMessageDialog(getFrameSwing(), "SE AGREGO AUTOPARTE");	
												}										
							            		
							            	} catch (MiException e) {
												throw e;
											}catch(Exception e)
											{
												throw new MiException("\n[FINALIZAR REPARACION] AGREGAR AUTOPARTE- FINALIZAR REPARACION EXCEPTION :"+e);
											}
							            }});
										panelOpcion.add(btAdd);
										
										///################# SUBMIT ##########################
										JButton btSubmit=new JButton();
										btSubmit.setText("SUBMIT");								
										btSubmit.addMouseListener(new MouseAdapter() {												
							            @Override
							            public void mouseReleased(MouseEvent evt) {
							            	
							            	try
							            	{
							            		if((getReparacionAux().getAutopartes()==null)&&(getReparacionAux().getAutopartes().isEmpty())&&(getReparacionAux().getAutopartes().size()==0))	
							            		{
								            		JOptionPane.showMessageDialog(getFrameSwing(), "NO SE AGREGO AUTOPARTE");
							            		}else
									            {    
													//Podria validar si se cargo autopartes o no se cargo nada
							            			reparacionBo.insertarupdateReparacionFinal(getReparacionAux(),reparacionBo.buscarUltimaReparacionAutoparteId());
													int indice=0;
													for(Reparacion repara:getReparacionesG())
													{
														if(repara.getCliente().getId()==getReparacionAux().getCliente().getId())
														{
															break;
														}
														indice++;
													}
													getReparacionesG().remove(indice);
													getReparacionesG().add(getReparacionAux());	
													
													JOptionPane.showMessageDialog(getFrameSwing(), "REPARACION FINALIZADA OK");
												 }
							            		 pantallaPrincipal(getFrameSwing());
												
							            	} catch (MiException e) {
												throw e;
											}catch(Exception e)
											{
												throw new MiException("\n[MENU SISTEMA] SUBMIT - FINALIZAR REPARACION EXCEPTION :"+e);
											}
										
										
							            }});
										panelOpcion.add(btSubmit);
										
										//panel.add(BorderLayout.SOUTH,panelOpcion);
											
										getPanelInicio().add(panel,BorderLayout.CENTER);
										getPanelInicio().add(panelOpcion,BorderLayout.SOUTH);
										getFrameSwing().setContentPane(getPanelInicio());
										
										break;
									}
								}
							}
						}
	        		}	
				} catch (MiException e) {
					throw e;					
		        } catch (Exception e) {
					throw new MiException("[INICIAR REPARACION]EXCEPTION : "+e);												
				}
			}
			});
		
		//////////// MOSTRAR PENDIENTES ////////////
		itemReparaPendientes.addActionListener(new ActionListener(){
			@Override
		public void actionPerformed(ActionEvent arg0) {
			
				try
            	{	
					setFrameSwing(getFrameSwing());		
					getFrameSwing().getContentPane().removeAll();
					getFrameSwing().getContentPane().repaint();
					getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
					
					JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
					JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.white,new Dimension(400,400),null);
					JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR REPARACIONES PENDIENTES",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);

			    	//JTable table=panelGestor.cargarPanelReparacionesEnReparacion(reparacionesG);										
					JTable table=panelGestor.cargarReparacionesPendEnTabla(getReparacionesG());
			        
			        panel.add(BorderLayout.NORTH,panelTitulo);
					panel.add(BorderLayout.CENTER,table);
					panel.add(BorderLayout.SOUTH,panelResto);
					getPanelInicio().add(panel,BorderLayout.CENTER);					
					
					getFrameSwing().setContentPane(getPanelInicio());
					
            	}catch(MiException e)
				{
					throw e;
				}catch(Exception e)
				{
					throw new MiException("\n[MOSTRAR CLIENTES]MOSTRAR DATOS CLIENTE Exception : "+e);
				}
		}
		});
		///////// MOSTRAR FINALIZADAS ///////////7
		itemReparaFinalizadas.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try
        	{	
				setFrameSwing(getFrameSwing());		
				getFrameSwing().getContentPane().removeAll();
				getFrameSwing().getContentPane().repaint();
				getPanelInicio().add(getMenuBar(),BorderLayout.NORTH);
				
				JPanel panel = panelGestor.crearPanelBorderLayout(new BorderLayout(),null,Color.white,null);
				JPanel panelResto=panelGestor.crearPanelGrid(new GridLayout(1,1),null,Color.white,new Dimension(400,400),null);
				JPanel panelTitulo = panelGestor.crearPanelBorderConTitulo(new BorderLayout(),null,Color.black,new Dimension(400,50),"MOSTRAR REPARACIONES FINALIZADAS",JLabel.CENTER,new Font(Font.SERIF,Font.BOLD,15),Color.white);

		    	JPanel panelReparaciones=panelGestor.cargarPanelReparacionesEntregadas(getReparacionesG());										
				//JTable table=panelGestor.cargarReparacionesFinEnTabla(getReparacionesG());
		        panel.add(BorderLayout.NORTH,panelTitulo);
				panel.add(BorderLayout.CENTER,panelReparaciones);
				panel.add(BorderLayout.SOUTH,panelResto);
				getPanelInicio().add(panel,BorderLayout.CENTER);					
				
				getFrameSwing().setContentPane(getPanelInicio());
				
        	}catch(MiException e)
			{
				throw e;
			}catch(Exception e)
			{
				throw new MiException("\n[MOSTRAR CLIENTES]MOSTRAR DATOS CLIENTE Exception : "+e);
			}
			
		}
		});
		menuReparacion.add(itemIniciarReparacion);		
		menuReparacion.add(itemFinalizarReparacion);
		menuReparacion.add(itemReparaPendientes);
		menuReparacion.add(itemReparaFinalizadas);
		//////////////
		
		////////// INICIO /////////////
		JButton btInicio=new JButton("Inicio");
		btInicio.setAlignmentX(JButton.RIGHT);
		btInicio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pantallaPrincipal(getFrameSwing());
			}});
		
		//////////////////////////////////////////////////////////////////////////////////////
		menuBar.add(menuUsuario);		
		menuBar.add(menuCliente);
		menuBar.add(menuAutoparte);
		menuBar.add(menuReparacion);
		menuBar.add(btInicio);
		
		
		
		//frame.setJMenuBar(menuBar);
		//frame.setLayout(new BoxLayout());
		//frame.setLayout(new BorderLayout());
		
		this.getPanelInicio().add("North",menuBar);
		
		this.getFrameSwing().setContentPane(this.getPanelInicio());
		//frame.getContentPane().add(menuBar,BorderLayout.NORTH);
		 //frame.getContentPane().add(new JLabel("Drag to move", JLabel.CENTER),BorderLayout.CENTER);
		this.getFrameSwing().setVisible(true);
	}
	
}
