package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controller.MontadoraController;
import model.vo.Montadora;

public class ExclusaoMontadoraGUI {

	private JFrame frmExclusaoDeMontadoras;
	private JComboBox cbExcluir;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExclusaoMontadoraGUI window = new ExclusaoMontadoraGUI();
					window.frmExclusaoDeMontadoras.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ExclusaoMontadoraGUI() {
		initialize();
	}

	private void initialize() {
		frmExclusaoDeMontadoras = new JFrame();
		frmExclusaoDeMontadoras.setTitle("Exclusão de montadoras");
		frmExclusaoDeMontadoras.setBounds(100, 100, 385, 160);
		frmExclusaoDeMontadoras.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExclusaoDeMontadoras.getContentPane().setLayout(null);

		//TODO preencher o combobox com montadoras direto do banco
		cbExcluir = new JComboBox();
		this.atualizarComboBox();
		cbExcluir.setSelectedIndex(-1);
		cbExcluir.setBounds(10, 40, 350, 27);
		frmExclusaoDeMontadoras.getContentPane().add(cbExcluir);

		JLabel lblSelecioneUmaMontadora = new JLabel("Selecione uma montadora para excluir:");
		lblSelecioneUmaMontadora.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneUmaMontadora.setBounds(10, 20, 350, 20);
		frmExclusaoDeMontadoras.getContentPane().add(lblSelecioneUmaMontadora);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MontadoraController controller = new MontadoraController();
				String mensagem = "";
				
				
				//TODO implementar (questão 2 da avaliação
				
				//Mostrar mensagem após chamar a exclusão (sucesso/erro)
				mensagem = controller.excluirMontadora((Montadora) cbExcluir.getSelectedItem());
				if (!mensagem.equals("")) {
					JOptionPane.showMessageDialog(null, mensagem, "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				//Atualizar o combo de montadoras após excluir
				atualizarComboBox();
			}
		});
		btnExcluir.setBounds(120, 70, 120, 30);
		frmExclusaoDeMontadoras.getContentPane().add(btnExcluir);
	}
	
	public void atualizarComboBox() {
		MontadoraController controller = new MontadoraController();
		ArrayList<Montadora> montadora = controller.consultarMontadoras();
		cbExcluir.setModel(new DefaultComboBoxModel(montadora.toArray()));
	}
}
