public class Garcom extends Funcionario {
    private double valorFixo;
    private int horasTrabalhadasPorDia;
    private int diasTrabalhados;

    public Garcom(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadasPorDia, int diasTrabalhados) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadasPorDia = horasTrabalhadasPorDia;
        this.diasTrabalhados = diasTrabalhados;
    }


}
