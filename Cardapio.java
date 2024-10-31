import java.util.ArrayList;

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