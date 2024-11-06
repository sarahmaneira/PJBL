import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Interface extends JFrame {
    private Garcom garcom;
    private Chefe chefe;

   public Interface() {
       setTitle("Taisho");
       setSize(400, 300);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new FlowLayout());
       setVisible(true);

       //Botão para criar garçom
       JButton botaoCriarGarcom = new JButton("Adicionar Garçom");
       botaoCriarGarcom.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               garcom = new Garcom("João", "Garçom", 25,30);
           }
       });
   }
}