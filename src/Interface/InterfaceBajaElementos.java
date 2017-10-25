package Interface;

import java.awt.BorderLayout;import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorUsuario;
import Controlador.ControladorElementos;

import Entidades.Elemento;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class InterfaceBajaElementos extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfIDElemento;
	private ControladorElementos cre = new ControladorElementos();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceBajaElementos frame = new InterfaceBajaElementos();
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
	public InterfaceBajaElementos() {
		setTitle("Baja Tipo Elementos");
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblIDelemento = new JLabel("ID Elemento");
		
		tfIDElemento = new JTextField();
		tfIDElemento.setEnabled(false);
		tfIDElemento.setColumns(10);
		
		JButton btnBaja = new JButton("BAJA");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickbaja();
			}
		});
		
		JButton btnBuscarTodosLos = new JButton("Buscar Tipos Elementos");
		btnBuscarTodosLos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConsultarTodosTiposElementos();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIDelemento)
								.addComponent(btnBaja))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBuscarTodosLos)
								.addComponent(tfIDElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIDelemento)
						.addComponent(tfIDElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBaja)
						.addComponent(btnBuscarTodosLos))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SelecionarTipo();
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void SelecionarTipo() {
		int i = table.getSelectedRow();
		if(i!=-1){
			
			tfIDElemento.setText(table.getValueAt(i, 0).toString());
			
		}
		
	}

	protected void ConsultarTodosTiposElementos() {		
		DefaultTableModel dfm= new DefaultTableModel();	
		ArrayList<Elemento> el =cre.ConsultaTodosTiposElementos();
		table = this.table;
		table.setModel(dfm);
		dfm.setColumnIdentifiers(new Object[]{"ID","Nombre","Cantidad"});				
			
		
					
						for (int i=0;i<el.size();i++) {						
						{							
							
							
							dfm.addRow(new Object[]{el.get(i).getId_elemento(),el.get(i).getNombre_elemento(),el.get(i).getCantidad_elemento()});
							
							
						}
						}				
		
	}

	protected void clickbaja() {
		Elemento e = new Elemento();				
		e.setId_elemento(Integer.parseInt(tfIDElemento.getText()));
		cre.Baja(e);
		}
}
