class Pedido {
    int numero;
    String descricao;
    int valor;
    int quantidade;

    public Pedido(int numero, String descricao, int valor, int quantidade){
        this.numero = numero;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    public double calcularTotal(){
        return valor * quantidade;
    }

    public void mostrarPedido(){
        System.out.println("Pedido: " + descricao + " | Quantidade: " + quantidade + " | Total: R$ " + calcularTotal());
    }
}
