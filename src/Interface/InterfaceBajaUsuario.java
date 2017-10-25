package Interface;

import java.awt.BorderLayout;import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controlador.ControladorElementos;
import Controlador.ControladorUsuario;

import Datos.DatosUsuarios;
import Entidades.Elemento;
import Entidades.Persona;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfaceBajaUsuario extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfIdUsuario;
	private JTable table;
	ControladorUsuario cru = new ControladorUsuario();
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					InterfaceBajaUsuario frame = new InterfaceBajaUsuario();
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
	public InterfaceBajaUsuario() {
		setTitle("Baja Usuario");
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		
		
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblIdUsuario = new JLabel("ID Usuario: ");
		
		tfIdUsuario = new JTextField();
		tfIdUsuario.setEnabled(false);
		tfIdUsuario.setColumns(10);
		
		JButton btnBajaUsuario = new JButton("Baja Usuario");
		btnBajaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BajaUsuarioClick();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnMostrarUsuarios = new JButton("Mostrar Usuarios");
		btnMostrarUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConsultarTodosBajausuarios();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblIdUsuario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfIdUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnBajaUsuario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnMostrarUsuarios)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdUsuario)
						.addComponent(tfIdUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBajaUsuario))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(btnMostrarUsuarios)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Seleccion();
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	protected void Seleccion() {
		int i = table.getSelectedRow();
		if(i!=-1){
			
			tfIdUsuario.setText(table.getValueAt(i, 0).toString());
		
	}
		}

	protected void ConsultarTodosBajausuarios() {
		ArrayList<Persona> pe = new ArrayList<Persona>();
		DefaultTableModel dfm= new DefaultTableModel();	
		table = this.table;
		table.setModel(dfm);
		dfm.setColumnIdentifiers(new Object[]{"ID Usuarios","Nombre","Apellido"});
		DatosUsuarios da= new DatosUsuarios();		
			pe=cru.ConsultaTodosUsuarios();
			for (int i = 0; i < pe.size(); i++) {
				dfm.addRow(new Object[]{pe.get(i).getIdUsuario(),pe.get(i).getNombre(),pe.get(i).getApellido()});
														
						}
					
		
	}

	protected void BajaUsuarioClick() {
	
		
		Persona p = new Persona();
		p.setIdUsuario(Integer.parseInt(tfIdUsuario.getText()));
		
		cru.BajaPersonas(p);
		
		
	}
}
