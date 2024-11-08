import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {


    public Interface() {

        setTitle("Taisho");
        setSize(300, 200); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a janela ao clicar no X
        setLocationRelativeTo(null); // Centraliza a janela na tela


        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));



        JButton btnGerenciarRestaurante = new JButton("Gerenciar Restaurante");
        btnGerenciarRestaurante.setFocusPainted(false);
        btnGerenciarRestaurante.setBackground(new Color(0,0,0));
        btnGerenciarRestaurante.setForeground(Color.WHITE);
        btnGerenciarRestaurante.setFont(new Font("Calibri",Font.BOLD,14));
        btnGerenciarRestaurante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InterfaceAberto();
                dispose();
            }
        });

        JButton btnModoCliente = new JButton("Modo Cliente");
        btnModoCliente.setFocusPainted(false);
        btnModoCliente.setBackground(new Color(0,0,0));
        btnModoCliente.setForeground(Color.WHITE);
        btnModoCliente.setFont(new Font("Calibri",Font.BOLD,14));
        btnModoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InterfaceCliente();
                dispose();
            }
        });


        add(btnGerenciarRestaurante);
        add(btnModoCliente);


        setVisible(true);
    }


    public void fecharRestaurante(){
        JOptionPane.showMessageDialog(this, "Restaurante Fechado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }





    public static void main(String[] args) {
        new Interface(); // Cria a janela
    }
}
