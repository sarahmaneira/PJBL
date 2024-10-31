import java.util.ArrayList;
import java.io.*;

class PJBL {
    public static void main(String[] args) throws IOException {
        FileInputStream fileIn = new FileInputStream("pratos.ser");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Prato p = (Prato) objIn.readObject();

        objIn.close();
        fileIn.close();
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

class Prato{
    private String nome;
    private double valor;
    private String descricao;

    public Prato(String nome, double valor, String descricao){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getValor(){
        return valor;
    }
}

class Cliente{
    String nome;
    String numeroTelefone;
    String pedidos = "";

    public void fazerPedido(String novoPedido){
        if (pedidos.isEmpty()){
            pedidos += ", ";
        }
        pedidos += novoPedido;
    }

    public String consultarPedido() {
        if (pedidos.isEmpty()) {
            return "Nenhum pedido realizado.";
        }
        return pedidos;
    }
}

class Pedido{
    int numero;
    String descricao;
    int valor;

    public void calcularTotal(){
    }

    public void mostrarPedido(){

    }

}

class Cardapio{
    private ArrayList<Prato> pratos = new ArrayList<>();

    public void adicionarPrato(Prato prato){
        pratos.add(prato);
        System.out.println("Prato adicionado:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição: " + prato.getDescricao());
    }

    public void removerPrato(Prato prato){
        if (pratos.remove(prato)){
            System.out.println("Prato removido:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição" + prato.getDescricao());
        } else {
            System.out.println("Prato não encontrado no cardápio.");
        }
    }

    public void mostrarPratos(Prato prato){
        System.out.println("Pratos no cardápio:");
        for (Prato pratos : pratos){
            System.out.println("Prato:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição: " + prato.getDescricao());
        }
    }
}

class pedidoInvalidoException extends Exception{
    public pedidoInvalidoException(){
        super();
    }

    @Override
    public String toString() {
        return "Nenhum prato selecionado";
    }
}









