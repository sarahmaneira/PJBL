class Garcom extends Funcionario {
    private double valorFixo;
    private int horasTrabalhadas;

    public Garcom(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorFixo() {
        return valorFixo;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }
}