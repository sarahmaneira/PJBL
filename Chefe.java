import java.util.ArrayList;

public class Chefe extends Funcionario {
    private String identificador;
    private double valorFixo;
    private int horasTrabalhadas;
    private ArrayList<Prato> prato = new ArrayList<>();

    public Chefe(String nome, String cargo, int idade, double salario, String genero, double valorFixo, int horasTrabalhadas, String identificador) {
        super(nome, cargo, idade, salario, genero);
        this.valorFixo = valorFixo;
        this.horasTrabalhadas = horasTrabalhadas;
        this.identificador = identificador;
    }

    public void adicionarPrato(Prato p){
        prato.add(p);
    }

    public String getIdentificador(){
        return identificador;
    }

   public double getValorFixo(){
        return valorFixo;
   }

   public int getHorasTrabalhadas(){
        return horasTrabalhadas;
   }
}