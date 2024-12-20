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
    private Map<String, Double> precosPratos;

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
        pratosAdicionados = new HashMap<>();

        setVisible(true);
    }

    public void Sair(){
        dispose();
    }

    private void JanelaInfo(){
        JInternalFrame janelaInf = new JInternalFrame("Cardapio",true,false,false,false);
        janelaInf.setSize(687,445);
        janelaInf.setLayout(new FlowLayout());

        String[] colunas = {"Nome", "Preço", "Descrição", "Adicionar"};

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0){
            public boolean isCellEditable(int row, int column){
                return column == 3;
            }

        };
        JTable tabelaPratos = new JTable(modeloTabela);

        tabelaPratos.setRowHeight(36);
        tabelaPratos.setFont(new Font("Calibri", Font.PLAIN, 14));
        tabelaPratos.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabelaPratos.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabelaPratos.getColumnModel().getColumn(2).setPreferredWidth(300);

        tabelaPratos.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tabelaPratos.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), tabelaPratos));
        tabelaPratos.getColumnModel().getColumn(3).setPreferredWidth(80);


        JScrollPane scrollPane = new JScrollPane(tabelaPratos);
        janelaInf.add(scrollPane, BorderLayout.CENTER);

        CarregarDados(modeloTabela);

        janelaInf.setVisible(true);
        desktopPane.add(janelaInf);

    }

    private void CarregarDados(DefaultTableModel modeloTabela){
        precosPratos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pratos.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    String nome = dados[0];
                    double preco = Double.parseDouble(dados[1]);
                    String descricao = dados[2];

                    precosPratos.put(nome, preco);


                    modeloTabela.addRow(new Object[]{nome, preco, descricao, "Add"});
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
                SalvarItens();
                Chefe chefe = GerenciarDados.getChefes();
                Garcom garcom = GerenciarDados.getGarcom();

                mostrarPedido();
                String mensagem = "Pedido registrado.\n" + chefe.realizarTarefa() + "\n" + garcom.realizarTarefa();
                JOptionPane.showMessageDialog(InterfaceCliente.this, mensagem);
            }
        });

        botaoComprar.add(btnCompra);
        add(botaoComprar, BorderLayout.SOUTH);
    }

    private void mostrarPedido() {
        JFrame telaPedido = new JFrame("Pedido Realizado");
        telaPedido.setSize(400, 300);
        telaPedido.setLocationRelativeTo(null);

        JTextArea areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        double total = 0;
        StringBuilder pedidoTexto = new StringBuilder("Itens do Pedido:\n\n");
        for (Map.Entry<String, Integer> entry : pratosAdicionados.entrySet()) {
            String prato = entry.getKey();
            int quantidade = entry.getValue();
            double preco = obterPreco(prato);
            pedidoTexto.append(prato).append(" - Quantidade: ").append(quantidade).append(" - Total: R$ ").append(preco * quantidade).append("\n");
            total += preco * quantidade;
        }
        pedidoTexto.append("\nTotal do Pedido: R$ ").append(total);

        areaPedido.setText(pedidoTexto.toString());
        telaPedido.add(new JScrollPane(areaPedido));

        telaPedido.setVisible(true);
    }

    private void SalvarItens() {

        System.out.println("Caminho do diretório atual: " + System.getProperty("user.dir"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("itens_adicionados.txt", true))) {
            for (Map.Entry<String, Integer> entry : pratosAdicionados.entrySet()) {
                String prato = entry.getKey();
                int quantidade = entry.getValue();
                double precoUnitario = obterPreco(prato);

                Pedido pedido = new Pedido(quantidade, prato, (int) precoUnitario, quantidade);

                writer.write("Prato: " + pedido.descricao + " - Quantidade: " + pedido.quantidade + " - Preço Unitário: R$ " + precoUnitario + " - Total: R$ " + pedido.calcularTotal());
                writer.newLine();

                pedido.mostrarPedido();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os itens.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private double obterPreco(String nomePrato){
        return precosPratos.getOrDefault(nomePrato, 0.0);
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
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table){
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    String nome = table.getValueAt(row, 0).toString();
                    pratosAdicionados.put(nome, pratosAdicionados.getOrDefault(nome, 0) + 1);
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Adicionar" : value.toString();
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
