import java.util.ArrayList;

class Restaurante{
    private String nome;
    private String endereco;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private Cardapio cardapio = new (Cardapio);

    public Restaurante (String nome, String endereco){
        this.nome = nome;
        this.endereco = endereco;
    }
}

abstract class Funcionario {
    private String nome;
    private String cargo;
    private int idade;
    private double salario;
    private String genero;

    public Funcionario(String nome, String cargo, int idade, double salario, String genero){
        this.nome = nome;
        this.cargo = cargo;
        this.idade = idade;
        this.salario = salario;
        this.genero = genero;
    }

    public void calcularSalario(double valorFixo, int horasTrabalhadas){
        salario = valorFixo * horasTrabalhadas;
    }
}

abstract class Chef extends Funcionario {
    private double valorFixo;
    private int horasTrabalhadas;

    public Chef(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
    }
}

abstract class Garcom extends Funcionario {
    private double valorFixo;
    private int horasTrabalhadas;

    public Garcom(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
    }
}






