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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterfaceAberto extends JFrame {

    private JDesktopPane desktopPane;
    private Map<String, Integer> pratosAdicionados;
    private Map<String, Double> precosPratos;
    private ArrayList<Chefe> chefes = new ArrayList<>();

    public InterfaceAberto() {
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
                Sair();
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

    private void JanelaInfo() {
        JInternalFrame janelaInf = new JInternalFrame("Cardápio", true, false, false, false);
        janelaInf.setSize(500, 450);
        janelaInf.setLayout(new FlowLayout());

        String[] colunas = {"Nome", "Preço", "Descrição", "Chefe", "Remover"};

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
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
        tabelaPratos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), tabelaPratos));
        tabelaPratos.getColumnModel().getColumn(4).setPreferredWidth(80);

        JScrollPane scrollPane = new JScrollPane(tabelaPratos);
        janelaInf.add(scrollPane, BorderLayout.CENTER);

        CarregarDados(modeloTabela);

        janelaInf.setVisible(true);
        desktopPane.add(janelaInf);
    }

    private void CarregarDados(DefaultTableModel modeloTabela) {
        precosPratos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pratos.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String nome = dados[0];
                    double preco = Double.parseDouble(dados[1]);
                    String descricao = dados[2];
                    String chefe = dados[3];

//                    precosPratos.put(nome, preco);

                    modeloTabela.addRow(new Object[]{nome, preco, descricao, chefe, "Rmv"});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

    private double getPreco(String nomePrato) {
        return precosPratos.getOrDefault(nomePrato, 0.0);
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

    private void JanelaGarcom(){
        JInternalFrame janelaGarcons = new JInternalFrame("Garçons", true, false, false, false);
        janelaGarcons.setSize(650, 210);
        janelaGarcons.setLayout(new FlowLayout());

        janelaGarcons.setLocation(535, 240);

        janelaGarcons.setVisible(true);
        desktopPane.add(janelaGarcons);
    }

    private void JanelaChefes(){
        JInternalFrame janelaChefes = new JInternalFrame("Chefes", true, false, false, false);
        janelaChefes.setSize(650, 210);
        janelaChefes.setLayout(new FlowLayout());


        janelaChefes.setLocation(535, 0);

        janelaChefes.setVisible(true);
        desktopPane.add(janelaChefes);
    }

    private void JanelaBotoes(){
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(  1, 3, 15, 5));


        JButton btnAdicionarPrato = new JButton("Adicionar Prato");
        btnAdicionarPrato.setFocusPainted(false);
        btnAdicionarPrato.setBackground(new Color(0,0,0));
        btnAdicionarPrato.setForeground(Color.WHITE);
        btnAdicionarPrato.setFont(new Font("Calibri",Font.BOLD,14));

        JButton btnAdicionarChefe = new JButton("Adicionar Chefe");
        btnAdicionarChefe.setFocusPainted(false);
        btnAdicionarChefe.setBackground(new Color(0,0,0));
        btnAdicionarChefe.setForeground(Color.WHITE);
        btnAdicionarChefe.setFont(new Font("Calibri",Font.BOLD,14));
        btnAdicionarChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarFormularioChefe();
            }
        });

        JButton btnRemoverChefe = new JButton("Remover Chefe");
        btnRemoverChefe.setFocusPainted(false);
        btnRemoverChefe.setBackground(new Color(0,0,0));
        btnRemoverChefe.setForeground(Color.WHITE);
        btnRemoverChefe.setFont(new Font("Calibri",Font.BOLD,14));

        btnRemoverChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Chefe removido.");
            }
        });


        btnAdicionarPrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarPrato();
            }
        });

        painelBotoes.add(btnAdicionarChefe);
        painelBotoes.add(btnRemoverChefe);
        painelBotoes.add(btnAdicionarPrato);

        painelBotoes.setBounds(375, 500, 400, 200);

        desktopPane.add(painelBotoes);
    }

    private void mostrarChefes(JPanel painelChefes){
        painelChefes.removeAll(); // Limpa o painel antes de adicionar os chefes
        for (Chefe chefe : chefes) {
            JLabel labelChefe = new JLabel("Nome: " + chefe.getNome() + ", ID: " + chefe.getIdentificador() + ", Salário: " + chefe.getSalario());
            painelChefes.add(labelChefe);
        }
        painelChefes.revalidate();
        painelChefes.repaint();
    }

    private void criarPrato(){

    }

    private void criarFormularioChefe(){
        JFrame formFrame = new JFrame("Adicionar Chefe");
        formFrame.setSize(400,400);
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(9,2,5,5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Chefe"));

        JTextField nomeField = new JTextField();
        JTextField cargoField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField valorFixoField = new JTextField();
        JTextField horasTrabalhadasField = new JTextField();
        JTextField identificadorField = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Cargo:"));
        formPanel.add(cargoField);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(idadeField);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(salarioField);
        formPanel.add(new JLabel("Gênero:"));
        formPanel.add(generoField);
        formPanel.add(new JLabel("Valor Fixo:"));
        formPanel.add(valorFixoField);
        formPanel.add(new JLabel("Horas Trabalhadas:"));
        formPanel.add(horasTrabalhadasField);
        formPanel.add(new JLabel("Identificador:"));
        formPanel.add(identificadorField);

        JButton adicionarChefeBtn = new JButton("Adicionar Chefe");
        adicionarChefeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    String cargo = cargoField.getText();
                    int idade = Integer.parseInt(idadeField.getText());
                    double salario = Double.parseDouble(salarioField.getText());
                    String genero = generoField.getText();
                    double valorFixo = Double.parseDouble(valorFixoField.getText());
                    int horasTrabalhadas = Integer.parseInt(horasTrabalhadasField.getText());
                    String identificador = identificadorField.getText();

                    Chefe novoChefe = new Chefe(nome, cargo, idade, salario, genero, valorFixo, horasTrabalhadas, identificador);
                    chefes.add(novoChefe);

                    mostrarChefes((JPanel) ((JInternalFrame) desktopPane.getComponentAt(375, 10)).getContentPane().getComponent(0));


                    formFrame.dispose();
                    JOptionPane.showMessageDialog(InterfaceAberto.this, "Chefe adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(adicionarChefeBtn);
        formFrame.add(formPanel);
        formFrame.setVisible(true);
}
    }
