import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// modo gerente
public class InterfaceAberto extends JFrame{
    private JDesktopPane desktopPane;
    private ArrayList<Chefe> chefes = new ArrayList<>();
    private ArrayList<Garcom> garcons = new ArrayList<>();

    public InterfaceAberto(){
        setTitle("Gerenciamento Restaurante");
        setSize(700,580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Taisho",SwingConstants.LEFT);
        label.setFont(new Font("Ink Free",Font.BOLD,24));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout());

        add(label, BorderLayout.NORTH);

        setVisible(true);

        JButton btnFecharRestaurante = new JButton("Fechar Restaurante");
        btnFecharRestaurante.setFocusPainted(false);
        btnFecharRestaurante.setBackground(new Color(0,0,0));
        btnFecharRestaurante.setForeground(Color.WHITE);
        btnFecharRestaurante.setFont(new Font("Calibri",Font.BOLD,14));

        btnFecharRestaurante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FecharRestaurante();
                dispose();
            }
        });

        topPanel.add(btnFecharRestaurante, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);


        JanelaGarcom();
        JanelaChefes();
        JanelaPratos();
        JanelaBotoes();


        setVisible(true);

    }


    public void FecharRestaurante(){
        JOptionPane.showMessageDialog(this, "Restaurante Fechado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    private void JanelaGarcom(){
        JInternalFrame janelaGarcons = new JInternalFrame("Garçons", true, false, false, false);
        janelaGarcons.setSize(300, 200);
        janelaGarcons.setLayout(new FlowLayout());

        janelaGarcons.setLocation(10, 10);

        janelaGarcons.setVisible(true);
        desktopPane.add(janelaGarcons);
    }

    private void JanelaChefes(){
        JInternalFrame janelaChefes = new JInternalFrame("Chefes", false, false, false, false);
        janelaChefes.setSize(300, 200);
        janelaChefes.setLayout(new BorderLayout());

        JPanel painelChefes = new JPanel(new GridLayout(0,1));
        painelChefes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        mostrarChefes(painelChefes);

        janelaChefes.add(new JScrollPane(painelChefes),BorderLayout.CENTER);
        janelaChefes.setLocation(375, 10);
        janelaChefes.setVisible(true);
        desktopPane.add(janelaChefes);
    }

    private void JanelaPratos(){
        JInternalFrame janelaPratos = new JInternalFrame("Pratos", false,false,false,false);
        janelaPratos.setSize(300,200);
        janelaPratos.setLayout(new FlowLayout());

        JTextArea textAreaPratos = new JTextArea(10, 25);
        textAreaPratos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaPratos);

        janelaPratos.setLocation(10,250);

        janelaPratos.setVisible(true);
        desktopPane.add(janelaPratos);
    }

    private void JanelaBotoes(){
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 2, 10, 10));

        JButton btnAdicionarGarcom = new JButton("Adicionar Garçom");
        btnAdicionarGarcom.setFocusPainted(false);
        btnAdicionarGarcom.setBackground(new Color(0,0,0));
        btnAdicionarGarcom.setForeground(Color.WHITE);
        btnAdicionarGarcom.setFont(new Font("Calibri",Font.BOLD,14));
        btnAdicionarGarcom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarFormularioGarcom();
            }}) ;

        JButton btnRemoverGarcom = new JButton("Remover Garçom");
        btnRemoverGarcom.setFocusPainted(false);
        btnRemoverGarcom.setBackground(new Color(0,0,0));
        btnRemoverGarcom.setForeground(Color.WHITE);
        btnRemoverGarcom.setFont(new Font("Calibri",Font.BOLD,14));

        JButton btnAdicionarPrato = new JButton("Adicionar Prato");
        btnAdicionarPrato.setFocusPainted(false);
        btnAdicionarPrato.setBackground(new Color(0,0,0));
        btnAdicionarPrato.setForeground(Color.WHITE);
        btnAdicionarPrato.setFont(new Font("Calibri",Font.BOLD,14));
//        btnAdicionarPrato.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                criarFormularioPrato();
//            }
//        });

        JButton btnRemoverPrato = new JButton("Remover Prato");
        btnRemoverPrato.setFocusPainted(false);
        btnRemoverPrato.setBackground(new Color(0,0,0));
        btnRemoverPrato.setForeground(Color.WHITE);
        btnRemoverPrato.setFont(new Font("Calibri",Font.BOLD,14));

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

        btnRemoverGarcom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Garçom removido.");
            }
        });

        btnAdicionarPrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Prato Adicionado!");
            }
        });

        btnRemoverPrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Prato Removido.");
            }
        });

        painelBotoes.add(btnAdicionarGarcom);
        painelBotoes.add(btnRemoverGarcom);
        painelBotoes.add(btnAdicionarPrato);
        painelBotoes.add(btnRemoverPrato);
        painelBotoes.add(btnAdicionarChefe);
        painelBotoes.add(btnRemoverChefe);

        painelBotoes.setBounds(375, 250, 300, 200); // Ajuste a posição conforme necessário



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

    private void criarFormularioGarcom() {
        JFrame formFrame = new JFrame("Adicionar Garçom");
        formFrame.setSize(400, 400);
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Garçom"));

        JTextField nomeField = new JTextField();
        JTextField cargoField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField valorFixoField = new JTextField();
        JTextField horasTrabalhadasField = new JTextField();
        JTextField diasTrabalhadosField = new JTextField();

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
        formPanel.add(new JLabel("Dias Trabalhados:"));
        formPanel.add(diasTrabalhadosField);

        JButton adicionarGarcomBtn = new JButton("Adicionar Garçom");
        adicionarGarcomBtn.addActionListener(new ActionListener() {
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
                    int diasTrabalhados = Integer.parseInt(diasTrabalhadosField.getText());

                    Garcom novoGarcom = new Garcom(nome, cargo, idade, salario, genero, valorFixo, horasTrabalhadas, diasTrabalhados);
                    garcons.add(novoGarcom);

                    // Atualizar a lista de garçons na interface (você pode adicionar um método mostrarGarcons() semelhante ao mostrarChefes())
                    formFrame.dispose();
                    JOptionPane.showMessageDialog(InterfaceAberto.this, "Garçom adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(adicionarGarcomBtn);

        formFrame.add(formPanel);
        formFrame.setVisible(true);
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

                    // Atualizar a lista de chefes na interface
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

