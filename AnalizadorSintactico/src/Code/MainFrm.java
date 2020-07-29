package Code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.awt.event.ActionEvent;
import java_cup.runtime.Symbol;


public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField txtIn;
	private JTextField txtOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtIn = new JTextField();
		txtIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIn.setBounds(12, 13, 241, 53);
		panel.add(txtIn);
		txtIn.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Cleaning textBox
				txtOut.setText("");

				analize();
			}
		});
		btnNewButton.setBounds(258, 13, 152, 53);
		panel.add(btnNewButton);
		
		txtOut = new JTextField();
		txtOut.setBounds(12, 79, 398, 151);
		panel.add(txtOut);
		txtOut.setColumns(10);
				
		this.setLocationRelativeTo(null);

	}
	
	private void analize() {
		String ST = txtIn.getText();
		Sintax s = new Sintax(new Code.LexerCup(new StringReader(ST)));
		
		try {
			Object result = s.parse().value;
			txtOut.setText("Análisis realizado correctamente \n" + ST + " RESULTADO = " + result);
			txtOut.setForeground(new Color(25, 111, 61));
		} catch (Exception e) {
			Symbol sym = s.getS();
			txtOut.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
			txtOut.setForeground(Color.red);
		}
	}
}
