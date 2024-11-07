import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {

    // Construtor da janela principal
    public Interface() {
        // Configurações da janela principal
        setTitle("Restaurante");
        setSize(300, 200); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a janela ao clicar no X
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout da janela
        setLayout(new FlowLayout());

        // Botão que vai chamar o metodo abrir restaur
        JButton btnAbrirRestaurante = new JButton("Abrir Restaurante");
        btnAbrirRestaurante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRestaurante();
            }
        });

        // Adiciona o botão à janela
        add(btnAbrirRestaurante);

        // Tornar a janela visível
        setVisible(true);
    }

    // Método que é chamado quando o botão é clicado
    public void abrirRestaurante() {
        // Aqui você pode colocar qualquer lógica que deseja executar
        JOptionPane.showMessageDialog(this, "Restaurante Aberto!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método main para iniciar a aplicação
    public static void main(String[] args) {
        new Interface(); // Cria a janela
    }
}
