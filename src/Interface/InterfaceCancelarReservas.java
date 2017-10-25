package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorReservas;
import Entidades.Reservas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InterfaceCancelarReservas extends JInternalFrame {

	private JPanel contentPane;
	private JTextField tfIDReserva;
	private JTextField tfUsuario;
	private JTable table;
	ControladorReservas crr= new ControladorReservas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCancelarReservas frame = new InterfaceCancelarReservas();
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
	public InterfaceCancelarReservas() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cancelar Reservas");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBuscarReservas = new JButton("Buscar Reservas");
		btnBuscarReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarReserva();
			}
		});
		
		JLabel lblIdReserva = new JLabel("ID Reserva");
		
		tfIDReserva = new JTextField();
		tfIDReserva.setEnabled(false);
		tfIDReserva.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		tfUsuario = new JTextField();
		tfUsuario.setEnabled(false);
		tfUsuario.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CancelarReserva();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscarReservas))
							.addGap(24))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUsuario)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblIdReserva)
									.addGap(18)
									.addComponent(tfIDReserva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addComponent(btnCancelarReserva)
							.addGap(51))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(btnBuscarReservas)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnCancelarReserva))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsuario)
								.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
									.addComponent(tfIDReserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(33))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblIdReserva)
									.addContainerGap())))))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SeleccionarReserva();
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void SeleccionarReserva() {
		int i = table.getSelectedRow();
		if(i!=-1){
			
			tfIDReserva.setText(table.getValueAt(i, 0).toString());
			tfUsuario.setText(table.getValueAt(i, 1).toString());
			
		}
		
	}

	protected void BuscarReserva() {
		ArrayList<Reservas> re = new ArrayList<Reservas>();
	ResultSet rs=null;				
	re=crr.ConsultarTodasReservas();	
	DefaultTableModel dfm= new DefaultTableModel();	
	table = this.table;
	table.setModel(dfm);		
	dfm.setColumnIdentifiers(new Object[]{"ID Reserva","Usuario","Elemento ","Tipo Elemento"});				
		
	for (int i=0;i<re.size();i++) {						
		{
			dfm.addRow(new Object[]{re.get(i).getIdreservas(),re.get(i).getUsuario(),re.get(i).getElemento(),re.get(i).getTipoElemento(),});
			
			
		}					
	
}
}	
	

	protected void CancelarReserva() {
		
		int idReserva=Integer.parseInt(tfIDReserva.getText());
		crr.CancelarReserva(idReserva);
		JOptionPane.showMessageDialog(null, "Reserva Cancelada");
		
		
	}

}
