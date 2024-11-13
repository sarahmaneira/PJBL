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

    public void adicionarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }


    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public double calcularTotalPedidos() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.calcularTotal();
        }
        return total;
    }

}
