import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chefe extends Funcionario {
    private String identificador;
    private double valorFixo;
    private int horasTrabalhadas;
    private ArrayList<Prato> prato = new ArrayList<>();

    public Chefe(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public void realizarTarefa() {
        System.out.println("O chefe " + getNome() + " está supervisionando a cozinha e criando novos pratos.");
    }

    public void adicionarPrato(Prato p){prato.add(p);}

    
        
    }

     public static Chefe exibirFormularioChefe() {
        JTextField nomeField = new JTextField(20);
        JTextField cargoField = new JTextField(20);
        JTextField idadeField = new JTextField(20);
        JTextField salarioField = new JTextField(20);
        JTextField generoField = new JTextField(20);
        JTextField valorFixoField = new JTextField(20);
        JTextField horasTrabalhadasField = new JTextField(20);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10)); // 3 linhas, 2 colunas, com espaçamento de 10 pixels

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

        int option = JOptionPane.showConfirmDialog(null, formPanel, "Adicionar Chefe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cargo = cargoField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            double salario = Double.parseDouble(salarioField.getText());
            String genero = generoField.getText();
            double valorFixo = Double.parseDouble(valorFixoField.getText());
            int horasTrabalhadas = Integer.parseInt(horasTrabalhadasField.getText());

            return new Chefe(nome, cargo, idade, salario, genero, valorFixo, horasTrabalhadas);
        }
        return null;
    }

    public String getIdentificador() {
        return identificador;
    }

    public double getValorFixo() {
        return valorFixo;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    @Override
    public String toString() {
        return "Chefe: " + getNome() + ", ID: " + identificador + ", Salário: R$" + getSalario();
    }
}
