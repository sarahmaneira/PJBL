import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfaceAberto extends JFrame {
    private JDesktopPane desktopPane;
    protected Map<String, Integer> pratosAdicionados;
    protected Map<String, Double> precosPratos;
    private ArrayList<Chefe> chefes = new ArrayList<>();
    GerenciarDados gerenciador = new GerenciarDados();
    private JInternalFrame janelaChefes;
    private JPanel painelChefes;
    Interface interfacep = new Interface();


    public InterfaceAberto() throws Erro {
        setTitle("Taisho Restaurante");
        setSize(1200, 880);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Taisho", SwingConstants.LEFT);
        label.setFont(new Font("Ink Free", Font.BOLD, 24));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout());

        add(label, BorderLayout.NORTH);

        setVisible(true);

        JButton btnFecharRestaurante = new JButton("Fechar Restaurante");
        btnFecharRestaurante.setFocusPainted(false);
        btnFecharRestaurante.setBackground(new Color(0, 0, 0));
        btnFecharRestaurante.setForeground(Color.WHITE);
        btnFecharRestaurante.setFont(new Font("Calibri", Font.BOLD, 14));
        btnFecharRestaurante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfacep.fecharRestaurante();
                dispose();
            }
        });
        topPanel.add(btnFecharRestaurante, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        JanelaInfo();
        pratosAdicionados = new HashMap<>();

        JanelaGarcom();
        JanelaChefes();
        JanelaBotoes();

        setVisible(true);
    }

    public void Sair() {
        JOptionPane.showMessageDialog(this, "Restaurante Fechado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    private void JanelaInfo() throws Erro {
        JInternalFrame janelaInf = new JInternalFrame("Cardápio", true, false, false, false);
        janelaInf.setSize(500, 450);
        janelaInf.setLayout(new FlowLayout());

        String[] colunas = {"Nome", "Preço", "Descrição", "Remover"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
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


        List<Prato> pratos = gerenciador.lerArquivo();
        for (Prato prato : pratos) {
            modeloTabela.addRow(new Object[]{prato.getNome(), prato.getValor(), prato.getDescricao(), "RMV"});
        }

        janelaInf.setVisible(true);
        desktopPane.add(janelaInf);
    }

    private void SalvarItens() {
        System.out.println("Caminho do diretório atual: " + System.getProperty("user.dir"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("itens_adicionados.txt", true))) {
            for (Map.Entry<String, Integer> entry : pratosAdicionados.entrySet()) {
                String prato = entry.getKey();
                int quantidade = entry.getValue();
                double precoUnitario = getPreco(prato);
                double total = precoUnitario * quantidade;

                writer.write("Prato: " + prato + " - Quantidade: " + quantidade + " - Preço Unitário: R$ " + precoUnitario + " - Total: R$ " + total);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os itens.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void RemoverPrato(String prato) {

        pratosAdicionados.remove(prato);

        try {
            BufferedReader br = new BufferedReader(new FileReader("pratos.csv"));
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (!dados[0].equals(prato)) {
                    sb.append(linha).append("\n");
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("pratos.csv"));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover prato do cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Rmv" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    String nome = table.getValueAt(row, 0).toString();
                    RemoverPrato(nome);
                    ((DefaultTableModel) table.getModel()).removeRow(row); // Remove a linha da tabela
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Remover" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                JOptionPane.showMessageDialog(button, "Prato Removido");
            }
            isPushed = false;
            return label;
        }
    }

    private void JanelaGarcom() {
        JInternalFrame janelaGarcons = new JInternalFrame("Garçons", true, false, false, false);
        janelaGarcons.setSize(650, 210);
        janelaGarcons.setLayout(new FlowLayout());

        janelaGarcons.setLocation(535, 240);

        janelaGarcons.setVisible(true);
        desktopPane.add(janelaGarcons);
    }

    private void JanelaChefes() {
        JInternalFrame janelaChefes = new JInternalFrame("Chefes", true, false, false, false);
        janelaChefes.setSize(650, 210);
        janelaChefes.setLayout(new FlowLayout());
        janelaChefes.setLocation(535, 0);

        JPanel painelChefes = new JPanel();
        painelChefes.setLayout(new BoxLayout(painelChefes, BoxLayout.Y_AXIS));

        for (Chefe chefe : gerenciador.getChefes()) {
            JLabel chefeLabel = new JLabel("Nome: " + chefe.getNome());
            painelChefes.add(chefeLabel);
        }

        janelaChefes.add(painelChefes);
        janelaChefes.setVisible(true);
        desktopPane.add(janelaChefes);
    }

    private void atualizarJanelaChefes() {
        // Limpa o painel de chefes antes de adicionar novos dados
        painelChefes.removeAll();

        List<Chefe> chefes = gerenciador.getChefes(); // Pega a lista de chefes de GerenciarDados

        // Adiciona as informações de cada chefe como uma nova linha
        for (Chefe chefe : chefes) {
            JLabel labelChefe = new JLabel("Chefe: " + chefe.getNome());
            painelChefes.add(labelChefe);
        }

        // Atualiza a interface gráfica para mostrar as mudanças
        painelChefes.revalidate();
        painelChefes.repaint();
    }

    private void JanelaBotoes() {
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 3, 15, 5));

        Cardapio cardapio = new Cardapio();
//        Chefe chefe1 = new Chefe("Cristiano", "Chefe", 28, 4500.0, "Masculino", 204.55, 8);
//        Chefe chefe2 = new Chefe("Gustavo", "Chefe", 36, 4500.0, "Masculino", 204.55, 8);


        JButton btnAdicionarPrato = new JButton("Adicionar Prato");
        btnAdicionarPrato.setFocusPainted(false);
        btnAdicionarPrato.setBackground(new Color(0, 0, 0));
        btnAdicionarPrato.setForeground(Color.WHITE);
        btnAdicionarPrato.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdicionarPrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardapio.exibirFormularioAdicionarPrato();
            }
        });
        JButton btnAdicionarChefe = new JButton("Adicionar Chefe");
        btnAdicionarChefe.setFocusPainted(false);
        btnAdicionarChefe.setBackground(new Color(0, 0, 0));
        btnAdicionarChefe.setForeground(Color.WHITE);
        btnAdicionarChefe.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdicionarChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chefe novoChefe = Chefe.exibirFormularioChefe();
                if (novoChefe != null) {
                    gerenciador.adicionarChefe(novoChefe);
                    atualizarJanelaChefes();
                }
            }
        });

        JButton btnRemoverChefe = new JButton("Remover Chefe");
        btnRemoverChefe.setFocusPainted(false);
        btnRemoverChefe.setBackground(new Color(0, 0, 0));
        btnRemoverChefe.setForeground(Color.WHITE);
        btnRemoverChefe.setFont(new Font("Calibri", Font.BOLD, 14));

        btnRemoverChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this, "Chefe removido.");
            }
        });

        painelBotoes.add(btnAdicionarChefe);
        painelBotoes.add(btnRemoverChefe);
        painelBotoes.add(btnAdicionarPrato);

        painelBotoes.setBounds(250, 500, 700, 50);

        desktopPane.add(painelBotoes);
    }

    private double getPreco(String nomePrato) {
        return precosPratos.getOrDefault(nomePrato, 0.0);
    }
}

