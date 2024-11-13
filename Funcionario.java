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

    public String getCargo() {
        return cargo;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    public String getGenero() {
        return genero;
    }

    // Método abstrato que deve ser implementado pelas subclasses
    public abstract void realizarTarefa();
}
