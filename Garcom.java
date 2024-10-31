abstract class Garcom extends Funcionario {
    private double valorFixo;
    private int horasTrabalhadas;

    public Garcom(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
    }
}