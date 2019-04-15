package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.CarroController;
import controller.MontadoraController;
import model.vo.Carro;
import model.vo.Montadora;

public class ConsultaCarrosGUI {

	private JFrame frmConsultaCarros;
	private JTextField txtNome;
	private JComboBox cbMontadora;
	private List<Montadora> montadoras;
	private JTable tblCarros;
	private JComboBox cbExcluir;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCarrosGUI window = new ConsultaCarrosGUI();
					window.frmConsultaCarros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConsultaCarrosGUI() {
		initialize();
	}

	private void initialize() {

		frmConsultaCarros = new JFrame();
		frmConsultaCarros.setTitle("Consulta de carros");
		frmConsultaCarros.setBounds(100, 100, 534, 335);
		frmConsultaCarros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultaCarros.getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Placa");
		lblNome.setBounds(6, 20, 44, 15);
		frmConsultaCarros.getContentPane().add(lblNome);

		JLabel lblNivel = new JLabel("Montadora");
		lblNivel.setBounds(149, 20, 76, 15);
		frmConsultaCarros.getContentPane().add(lblNivel);
		
		JLabel label = new JLabel("Selecione uma montadora para excluir:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(87, 198, 350, 20);
		frmConsultaCarros.getContentPane().add(label);

		txtNome = new JTextField();
		txtNome.setBounds(46, 13, 90, 28);
		frmConsultaCarros.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		cbMontadora = new JComboBox();
		this.consultarMontadoras(); 
		cbMontadora.setModel(new DefaultComboBoxModel(montadoras.toArray()));
		cbMontadora.setSelectedIndex(-1);

		//Inicia sem nada selecionado no combo
		cbMontadora.setSelectedIndex(-1);

		cbMontadora.setBounds(223, 14, 165, 28);
		frmConsultaCarros.getContentPane().add(cbMontadora);

		JButton btnConsultarCarros = new JButton("Consultar");
		btnConsultarCarros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarroController controller = new CarroController();
				String nome = txtNome.getText();
				Montadora montadoraSelecionada = (Montadora) cbMontadora.getSelectedItem();
				ArrayList<Carro> carros = controller.consultarCarros(nome, montadoraSelecionada);
				
				controller.consultarCarros(nome, montadoraSelecionada);
				limparTela();
				atualizarTabelaCarros(carros);
				
			}
		});
		btnConsultarCarros.setBounds(387, 13, 125, 30);
		frmConsultaCarros.getContentPane().add(btnConsultarCarros);

		//Novo componente: tabela
		tblCarros = new JTable();
		tblCarros.setVisible(true);

		//Cria a tabela vazia apenas com as colunas
		tblCarros.setModel(new DefaultTableModel(
				new Object[][] {
					{"Placa", "Modelo", "Montadora", "Ano", "Valor"}
				},
				new String[] {
						"Placa", "Modelo", "Montadora", "Ano", "Valor"
				}
				));

		tblCarros.setBounds(6, 47, 506, 140);
		frmConsultaCarros.getContentPane().add(tblCarros);
		
		cbExcluir = new JComboBox();
		cbExcluir.setSelectedIndex(-1);
		cbExcluir.setModel(new DefaultComboBoxModel(montadoras.toArray()));
		cbExcluir.setBounds(87, 218, 350, 27);
		frmConsultaCarros.getContentPane().add(cbExcluir);
		
		JButton button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MontadoraController controller = new MontadoraController();
				String mensagem = "";
				
				//Mostrar mensagem após chamar a exclusão (sucesso/erro)
				mensagem = controller.excluirMontadora((Montadora) cbExcluir.getSelectedItem());
				if (!mensagem.equals("")) {
					JOptionPane.showMessageDialog(null, mensagem, "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				
				//Atualizar o combo de montadoras após excluir
				atualizarCbExcluir();
			}
		});
		button.setBounds(197, 248, 120, 30);
		frmConsultaCarros.getContentPane().add(button);
	}

	/**
	 * Atualiza o JTable de carros.
	 * @param carros a lista de carros que irá popular a tabela;
	 */
	protected void atualizarTabelaCarros(ArrayList<Carro> carros) {
		DefaultTableModel model = (DefaultTableModel) tblCarros.getModel();

		Object novaLinha[] = new Object[5];
		for(Carro carro: carros){
			novaLinha[0] = carro.getPlaca();
			novaLinha[1] = carro.getModelo();
			novaLinha[2] = carro.getAno();
			novaLinha[3] = "R$ " + String.valueOf(carro.getValor()).replace(".", ",");
			
			model.addRow(novaLinha);
		}
	}
	public void limparTela() {
		tblCarros.setModel(new DefaultTableModel(
			new Object[][] {{"Placa", "Modelo", "Ano", "Valor"}},
			new String[] {"Placa", "Modelo", "Ano", "Valor"}));
		}

	private void consultarMontadoras() {
		//TODO consultar as montadoras cadastradas no banco (item 1.b)
		CarroController controller = new CarroController();
		montadoras = controller.consultarMontadoras();
		cbMontadora.setModel(new DefaultComboBoxModel(montadoras.toArray()));
		cbMontadora.setSelectedIndex(-1);
	}
	
	public void atualizarCbExcluir() {
		MontadoraController controller = new MontadoraController();
		ArrayList<Montadora> montadora = controller.consultarMontadoras();
		cbExcluir.setModel(new DefaultComboBoxModel(montadora.toArray()));
	}
	
}