import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceCliente extends JFrame{

   private JDesktopPane desktopPane;

   public InterfaceCliente(){
       setTitle("Taisho Restaurante");
       setSize(700,580);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setLocationRelativeTo(null);

       setLayout(new BorderLayout());

       JPanel topPanel = new JPanel(new BorderLayout());
       topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       JLabel label = new JLabel("Taisho",SwingConstants.LEFT);
       label.setFont(new Font("Ink Free",Font.BOLD,24));
       label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       setLayout(new BorderLayout());

       add(label, BorderLayout.NORTH);

       setVisible(true);

       JButton btnFecharRestaurante = new JButton("Sair");
       btnFecharRestaurante.setFocusPainted(false);
       btnFecharRestaurante.setBackground(new Color(0,0,0));
       btnFecharRestaurante.setForeground(Color.WHITE);
       btnFecharRestaurante.setFont(new Font("Calibri",Font.BOLD,14));
       btnFecharRestaurante.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Sair();
               dispose();
           }
       });
       topPanel.add(btnFecharRestaurante, BorderLayout.EAST);
       add(topPanel, BorderLayout.NORTH);

       desktopPane = new JDesktopPane();
       add(desktopPane, BorderLayout.CENTER);

       JanelaInfo();
       BotaoCompra();

       setVisible(true);
   }

    public void Sair(){
        dispose();
    }

    private void JanelaInfo(){
       JInternalFrame janelaInf = new JInternalFrame("Cardapio",true,false,false,false);
       janelaInf.setSize(687,445);
       janelaInf.setLayout(new FlowLayout());

       janelaInf.setVisible(true);
       desktopPane.add(janelaInf);

    }

    private void BotaoCompra(){
       JPanel botaoComprar = new JPanel();
        botaoComprar.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

       JButton btnCompra = new JButton("Finalizar Compra");
        btnCompra.setFocusPainted(false);
        btnCompra.setBackground(new Color(0,0,0));
        btnCompra.setForeground(Color.WHITE);
        btnCompra.setFont(new Font("Calibri",Font.BOLD,14));

        btnCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceCliente.this,"Pedido registrado.");
            }
        });

        botaoComprar.add(btnCompra);
        add(botaoComprar, BorderLayout.SOUTH);
    }
}
