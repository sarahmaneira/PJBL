public abstract class Funcionario {
    private String nome;
    private String cargo;
    private int idade;
    private double salario;
    private String genero;

    public Funcionario(String nome, String cargo, int idade, double salario, String genero) {
        this.nome = nome;
        this.cargo = cargo;
        this.idade = idade;
        this.salario = salario;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public String getCargo() {
        return cargo;
    }

    public int getIdade() {
        return idade;
    }

    public String getGenero() {
        return genero;
    }


}
