package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class InterfaceAdmin extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAdmin frame = new InterfaceAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceAdmin() {
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 620);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnElementos = new JMenu("Elementos");
		menuBar.add(mnElementos);
		
		JMenu mnTiposElementos = new JMenu("Tipos Elementos");
		mnElementos.add(mnTiposElementos);
		
		JMenuItem mntmAltaElementos = new JMenuItem("Alta Tipo Elementos");
		mnTiposElementos.add(mntmAltaElementos);
		
		JMenuItem mntmBajaElementos = new JMenuItem("Baja Tipo Elementos");
		mnTiposElementos.add(mntmBajaElementos);
		
		JMenuItem mntmModificarElementos = new JMenuItem("Modificar Tipo Elementos");
		mnTiposElementos.add(mntmModificarElementos);
		
		JMenu mnElementosReserva = new JMenu("Elementos");
		mnElementos.add(mnElementosReserva);
		
		JMenuItem mntmAltaElementoRerserva = new JMenuItem("Alta Elemento");
		mntmAltaElementoRerserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInterfaceAltaElementoReserva();
			}
		});
		mnElementosReserva.add(mntmAltaElementoRerserva);
		
		JMenuItem mntmBajaElementoReserva = new JMenuItem("Baja Elemento");
		mntmBajaElementoReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowBajaElementoReserva();
			}
		});
		mnElementosReserva.add(mntmBajaElementoReserva);
		
		JMenuItem mntmModificarElemento = new JMenuItem("Modificar Elemento");
		mntmModificarElemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowModificarElemento();
			}
		});
		mnElementosReserva.add(mntmModificarElemento);
		mntmModificarElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInterfceModificacionElementos();
			}
		});
		mntmBajaElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInterfaceBajaElementos();
			}
		});
		mntmAltaElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showIntefaceAltaElementos();
			}
		});
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAltaUsuarios = new JMenuItem("Alta Usuarios");
		mntmAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaUsuario();
			}
		});
		mnUsuarios.add(mntmAltaUsuarios);
		
		JMenuItem mntmHabilitarinhabilitarUsuarios = new JMenuItem("Habilitar/Inhabilitar Usuarios");
		mntmHabilitarinhabilitarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowInterfaceHabilitar();
			}
		});
		
		JMenuItem mntmBajaUsuarios = new JMenuItem("Baja Usuarios");
		mntmBajaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowMostrarBajaUsuario();
			}
		});
		mnUsuarios.add(mntmBajaUsuarios);
		mnUsuarios.add(mntmHabilitarinhabilitarUsuarios);
		
		JMenu mnReservas = new JMenu("Reservas");
		menuBar.add(mnReservas);
		
		JMenuItem mntmCancelarReservas = new JMenuItem("Cancelar Reservas");
		mntmCancelarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowCancelarReservas();
			}
		});
		mnReservas.add(mntmCancelarReservas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}


	protected void ShowCancelarReservas() {
		InterfaceCancelarReservas icr= new InterfaceCancelarReservas();
		desktopPane.add(icr);
		icr.setVisible(true);
		
	}

	protected void ShowModificarElemento() {
		InterfaceModificarElemento ime= new InterfaceModificarElemento();
		desktopPane.add(ime);
		ime.setVisible(true);
		
	}

	protected void ShowMostrarBajaUsuario() {
		InterfaceBajaUsuario ibu = new InterfaceBajaUsuario();
		desktopPane.add(ibu);
		ibu.setVisible(true);
		
	}

	protected void ShowBajaElementoReserva() {
		InterfaceBajaElementoReserva ib= new InterfaceBajaElementoReserva();
		desktopPane.add(ib);
		ib.setVisible(true);
		
	}

	protected void showInterfaceAltaElementoReserva() {
		InterfaceAltaElementosReserva iaer = new InterfaceAltaElementosReserva();
		desktopPane.add(iaer);
		iaer.setVisible(true);
		
	}

	protected void ShowInterfaceHabilitar() {
		InterfaceHabilitarInhabilitarUsuarios ihiu = new InterfaceHabilitarInhabilitarUsuarios();
		desktopPane.add(ihiu);
		ihiu.setVisible(true);
		
	}

	protected void AltaUsuario() {
		InterfaceAltaUsuario iau = new InterfaceAltaUsuario();
		desktopPane.add(iau);
		iau.setVisible(true);
	}

	protected void showInterfceModificacionElementos() {
		InterfaceModificarTipoElementos ime = new InterfaceModificarTipoElementos();
		desktopPane.add(ime);
		ime.setVisible(true);
		
	}

	protected void showInterfaceBajaElementos() {

		InterfaceBajaElementos ibe = new InterfaceBajaElementos();
		desktopPane.add(ibe);
		ibe.setVisible(true);
		
	}

	protected void showIntefaceAltaElementos() {
	
		InterfaceAltaElementos iae = new InterfaceAltaElementos();
		desktopPane.add(iae);
		iae.setVisible(true);
		
		
	}
}
