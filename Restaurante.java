import java.util.ArrayList;

public class Restaurante {
    private String nome;
    private String endereco;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private Cardapio cardapio = new Cardapio();

    public Restaurante (String nome, String endereco){
        this.nome = nome;
        this.endereco = endereco;
    }

    public void abrirRestaurante(){
        System.out.println("Restaurante aberto");
    }

    public void fecharRestaurante(){
        System.out.println("Restaurante fechado");
    }

    // adicionar pedido
    public void adicionarPedido(){

    }
    // remover pedido
}
