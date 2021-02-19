package br.ifsul.trabLp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Batata extends JFrame implements ActionListener {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	private static final long serialVersionUID = 1L;
	private static JTextField tfNome = new JTextField();
	private static JTextField tfDataNasc = new JTextField();
	private static JTextField tfNacionalidade = new JTextField();
	private static JButton btCadastrar = new JButton("Salvar");
	
	public Batata() {

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblTituloSecao = new JLabel("Artista");
		lblTituloSecao.setForeground(Color.white);
		lblTituloSecao.setBackground(Color.RED);
		lblTituloSecao.setOpaque(true);
		painel.add(lblTituloSecao);
		// painel.add(new JLabel());
		
		JLabel lblNome = new JLabel("Nome");
		JLabel lblDataNasc = new JLabel("Data de Nascimento");
		JLabel lblNacionalidade = new JLabel("Nacionalidade");

		painel.add(lblNome);
		painel.add(tfNome);
		
		painel.add(lblDataNasc);
		painel.add(tfDataNasc);
		
		painel.add(lblNacionalidade);
		painel.add(tfNacionalidade);
		
		btCadastrar.addActionListener(this);
		btCadastrar.setActionCommand("CadastrarMusica");
		painel.add(btCadastrar);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(painel, BorderLayout.CENTER);
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String action = ae.getActionCommand();
		
        if (action.equals("CadastrarMusica")) {
        	
        	if (tfNome.getText().length() == 0 || tfDataNasc.getText().length() == 0 || tfNacionalidade.getText().length() == 0) {
        		System.out.println("Por favor, preencha nome, nacionalidade e data de nascimento para cadastrar um artista.");
        		return;
        	}
            
        	final Artista artista = new Artista();
        	artista.setNome(tfNome.getText());
        	artista.setNacionalidade(tfNacionalidade.getText());
        	artista.setDataNasc(Date.valueOf(tfDataNasc.getText()));
        	
        	artistaRepository.save(artista);
        }
	}	
}















