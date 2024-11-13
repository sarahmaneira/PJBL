import javax.swing.*;
import java.util.ArrayList;

public class Chefe extends Funcionario {
    private String identificador;
    private double valorFixo;
    private int horasTrabalhadas;
    private ArrayList<Prato> prato = new ArrayList<>();

    public Chefe(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas, String identificador) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
        this.identificador = identificador;
    }

    public void adicionarPrato(Prato p){
        prato.add(p);
    }

    public void exibirFormularioChefe(){
        JTextField nomeField = new JTextField();
        JTextField cargoField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField valorFixoField = new JTextField();
        JTextField horasTrabalhadasField = new JTextField();
        JTextField identificadorField = new JTextField();

        JPanel formPanel = new JPanel();
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

        int option = JOptionPane.showConfirmDialog(null, formPanel, "Adicionar Chefe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Quando o usuário clicar em OK, os dados do chefe são coletados
            String nome = nomeField.getText();
            String cargo = cargoField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            double salario = Double.parseDouble(salarioField.getText());
            String genero = generoField.getText();
            double valorFixo = Double.parseDouble(valorFixoField.getText());
            int horasTrabalhadas = Integer.parseInt(horasTrabalhadasField.getText());
            String identificador = identificadorField.getText();

            Chefe chefe = new Chefe(nome, cargo, idade, salario, genero, valorFixo, horasTrabalhadas, identificador);
        }
        }

    public String getIdentificador(){
        return identificador;
    }

   public double getValorFixo(){
        return valorFixo;
   }

   public int getHorasTrabalhadas(){
        return horasTrabalhadas;
   }
}