class Prato{
    private String nome;
    private double valor;
    private String descricao;
    private Chefe chefeResponsavel;

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

    public Chefe getChefeResponsavel(){ return chefeResponsavel; }

    public void setChefResponsavel(Chefe chefe) {
        this.chefeResponsavel = chefe;
    }
}

