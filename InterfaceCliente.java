import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class InterfaceCliente extends JFrame{

   private JDesktopPane desktopPane;
   private Map<String, Integer> pratosAdicionados;

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

       String[] colunas = {"Nome", "Preço", "Descrição", "Chefe", "Adicionar"};

       DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0){
           public boolean isCellEditable(int row, int column){
               return column == 4;
           }

       };
       JTable tabelaPratos = new JTable(modeloTabela);

       tabelaPratos.setRowHeight(36);
       tabelaPratos.setFont(new Font("Calibri", Font.PLAIN, 14));
       tabelaPratos.getColumnModel().getColumn(0).setPreferredWidth(90);
       tabelaPratos.getColumnModel().getColumn(1).setPreferredWidth(50);
       tabelaPratos.getColumnModel().getColumn(2).setPreferredWidth(300);
       tabelaPratos.getColumnModel().getColumn(3).setPreferredWidth(50);

       tabelaPratos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
       tabelaPratos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
       tabelaPratos.getColumnModel().getColumn(4).setPreferredWidth(80);


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
                    modeloTabela.addRow(new Object[]{dados[0], dados[1], dados[2], dados[3],"Add"});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void BotaoCompra(){
       JPanel botaoComprar = new JPanel();
        botaoComprar.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

       JButton btnCompra = new JButton("Finalizar Pedido");
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

    class ButtonRenderer extends JButton implements TableCellRenderer {
       public ButtonRenderer(){
           setOpaque(true);
       }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Add" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
       private JButton button;
       private String label;
       private boolean isPushed;

       public ButtonEditor(JCheckBox checkBox){
           super(checkBox);
           button = new JButton();
           button.setOpaque(true);
           button.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   fireEditingStopped();
               }
           });
       }

       @Override
       public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Add" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
       }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                JOptionPane.showMessageDialog(button, "Item adicionado!");
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

    }




}
