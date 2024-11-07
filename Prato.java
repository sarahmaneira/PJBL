class Prato{
    private String nome;
    private double valor;
    private String descricao;
    private String chefeResponsavel;

    public Prato(String nome, double valor, String descricao, String chefeResponsavel){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.chefeResponsavel = chefeResponsavel;
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

    public String getChefeResponsavel(){ return chefeResponsavel; }

}

