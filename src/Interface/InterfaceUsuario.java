package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceUsuario extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JMenuItem mntmConsultarTodos;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceUsuario frame = new InterfaceUsuario();
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
	public InterfaceUsuario() {
		setTitle("usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 786, 597);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnElementos = new JMenu("Elementos");
		menuBar.add(mnElementos);
		
		mntmConsultarTodos = new JMenuItem("Consultar Tipo Elementos");
		mntmConsultarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showConsultarTodos();
			}
		});
		mnElementos.add(mntmConsultarTodos);
		
		JMenuItem mntmConsultarElementos = new JMenuItem("Consultar Elementos");
		mntmConsultarElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowConsultarElementos();
			}
		});
		mnElementos.add(mntmConsultarElementos);
		
		JMenu mnReserva = new JMenu("Reserva");
		menuBar.add(mnReserva);
		
		JMenuItem mntmReservar = new JMenuItem("Reservar");
		mntmReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowReservar();
			}
		});
		mnReserva.add(mntmReservar);
		
		JMenuItem mntmCancelarReserva = new JMenuItem("Cancelar Reserva");
		mntmCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowCancelarReservaUsuario();
			}
		});
		mnReserva.add(mntmCancelarReserva);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	
	protected void ShowCancelarReservaUsuario() {
		InterfaceCancelarReservaUsuarios icr = new InterfaceCancelarReservaUsuarios();
		desktopPane.add(icr);
		icr.setVisible(true);
		
	}

	protected void ShowConsultarElementos() {
		InterfaceConsultarElementos ice = new InterfaceConsultarElementos();
		desktopPane.add(ice);
		ice.setVisible(true);
		
	}

	protected void ShowReservar() {
		InterfaceAltaReserva ir= new InterfaceAltaReserva();
		desktopPane.add(ir);
		ir.setVisible(true);
		
	}

	protected void showConsultarTodos() {
		InterfaceConsultarTodosTiposElementos ice = new InterfaceConsultarTodosTiposElementos();
		desktopPane.add(ice);
		ice.setVisible(true);
		
		
	}
}
