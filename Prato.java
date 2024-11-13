public class Prato{
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

