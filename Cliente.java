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