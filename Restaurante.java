
class Prato{
    String nome;
    double valor;
    String descricao;

    public Prato(String nome, double valor, String descricao){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
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
}
