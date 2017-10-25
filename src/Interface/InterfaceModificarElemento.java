package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorUsuario;
import Controlador.ControladorElementos;
import Entidades.Elemento;

import javax.swing.event.PopupMenuEvent;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InterfaceModificarElemento extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfIDElemento;
	private JTextField tfNombreElemento;
	private JTextField tfTipoElemento;
	private JComboBox cboxTipos;
	ControladorElementos cre= new ControladorElementos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceModificarElemento frame = new InterfaceModificarElemento();
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
	public InterfaceModificarElemento() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Modificar Elemento");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cboxTipos = new JComboBox();
		cboxTipos.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				AgregarTipoElemento();
			}
		});
		cboxTipos.setModel(new DefaultComboBoxModel(new String[] {"Tipo Elementos"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombreElemento = new JLabel("Nombre Elemento");
		
		JLabel lblTipoElemento = new JLabel("Tipo Elemento");
		
		tfIDElemento = new JTextField();
		tfIDElemento.setEnabled(false);
		tfIDElemento.setColumns(10);
		
		tfNombreElemento = new JTextField();
		tfNombreElemento.setText("");
		tfNombreElemento.setColumns(10);
		
		tfTipoElemento = new JTextField();
		tfTipoElemento.setEnabled(false);
		tfTipoElemento.setText("");
		tfTipoElemento.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ModificarElemento();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BuscarTiposElementos();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(cboxTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBuscar)
					.addContainerGap(220, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombreElemento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfNombreElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(240, Short.MAX_VALUE))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTipoElemento)
					.addGap(18)
					.addComponent(tfTipoElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(243, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfIDElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
					.addComponent(btnModificar)
					.addGap(76))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboxTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(tfIDElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModificar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreElemento)
						.addComponent(tfNombreElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoElemento)
						.addComponent(tfTipoElemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SeleccionarElemento();
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	protected void ModificarElemento() {
		int id= Integer.parseInt(tfIDElemento.getText());
		String nombre= tfNombreElemento.getText();
		String tipo= tfTipoElemento.getText();		
		cre.ModificarElemento(id, nombre, tipo);
		
		
	}

	protected void BuscarTiposElementos() {
		
		ArrayList<Elemento> el= new ArrayList<Elemento>();
		
		Object TipoEl = cboxTipos.getSelectedItem();
		el=cre.ConsultaTodosElementos(TipoEl);
		DefaultTableModel dfm= new DefaultTableModel();	
		table = this.table;
		table.setModel(dfm);		
		dfm.setColumnIdentifiers(new Object[]{"ID Elemento","Nombre Elemento","Tipo Elemento"});		
		

		for (int i = 0; i < el.size(); i++) {
			dfm.addRow(new Object[] { el.get(i).getIdElementosReserva(), el.get(i).getNombreElementoReserva(),
					el.get(i).getTipoElementoReserva() });
		}						
					
				}
		
	

	protected void SeleccionarElemento() {
		int i = table.getSelectedRow();
		if(i!=-1){
			
			tfIDElemento.setText(table.getValueAt(i, 0).toString());
			tfNombreElemento.setText(table.getValueAt(i, 1).toString());
			tfTipoElemento.setText(table.getValueAt(i, 2).toString());
		}
			
		
	}

	protected void AgregarTipoElemento() {
		
		ArrayList<Elemento> el = new ArrayList<Elemento>();		
		 el= cre.AgregarTipos();

		cboxTipos.removeAllItems();


		for (int i = 0; i < el.size(); i++) {
			
				
				cboxTipos.addItem(el.get(i).getNombre_elemento());
			}

	}
		
	
}
