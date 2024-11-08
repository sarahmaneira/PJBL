import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

       String[] colunas = {"Nome", "Preço", "Descrição", "Chefe"};
       DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0){
           public boolean isCellEditable(int row, int column){
               return false;
           }

       };
       JTable tabelaPratos = new JTable(modeloTabela);

       tabelaPratos.setRowHeight(35);
       tabelaPratos.setFont(new Font("Calibri", Font.PLAIN, 14));

        tabelaPratos.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabelaPratos.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaPratos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabelaPratos.getColumnModel().getColumn(3).setPreferredWidth(150);

       JScrollPane scrollPane = new JScrollPane(tabelaPratos);
       janelaInf.add(scrollPane, BorderLayout.CENTER);

       CarregarDados(modeloTabela);

       janelaInf.setVisible(true);
       desktopPane.add(janelaInf);

    }

    private void CarregarDados(DefaultTableModel modeloTabela){
        try (BufferedReader br = new BufferedReader(new FileReader("pratos.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    modeloTabela.addRow(dados);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
