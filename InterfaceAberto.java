import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceAberto extends JFrame{

    private JDesktopPane desktopPane;

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
        JInternalFrame janelaChefes = new JInternalFrame("Chefes", true, false, false, false);
        janelaChefes.setSize(300, 200);
        janelaChefes.setLayout(new FlowLayout());

        janelaChefes.setLocation(375, 10);

        janelaChefes.setVisible(true);
        desktopPane.add(janelaChefes);
    }

    private void JanelaPratos(){
        JInternalFrame janelaPratos = new JInternalFrame("Pratos", false,false,false,false);
        janelaPratos.setSize(300,200);
        janelaPratos.setLayout(new FlowLayout());

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

        JButton btnRemoverChefe = new JButton("Remover Chefe");
        btnRemoverChefe.setFocusPainted(false);
        btnRemoverChefe.setBackground(new Color(0,0,0));
        btnRemoverChefe.setForeground(Color.WHITE);
        btnRemoverChefe.setFont(new Font("Calibri",Font.BOLD,14));


        btnAdicionarChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this, "Chefe Adicionado!");
            }
        });

        btnRemoverChefe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Chefe removido.");
            }
        });

        btnAdicionarGarcom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InterfaceAberto.this,"Garçom Adicionado!");
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
}