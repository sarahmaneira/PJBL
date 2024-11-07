import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenciarDados {
    private List<Prato>pratos;

    public List<Prato>lerArquivo(){
        String pratosArquivo = "pratos.csv";
        String separador = ";";

        List<List<String>> tabela = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pratosArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosPratos = linha.split(separador);
                tabela.add(Arrays.asList(dadosPratos));
                List<String> pratost = Arrays.asList(dadosPratos);

                for (int i = 0; i < tabela.size(); i++){
                    for (int j = 0; j < pratost.size(); j++) {
                        String nomePrato = tabela.get(i).get(j);
                        double valorPrato = Double.valueOf(tabela.get(i).get(j));
                        String descricaoPrato = tabela.get(i).get(j);
                        String chefeResponsavel = tabela.get(i).get(j);

                        Prato prato = new Prato(nomePrato, valorPrato, descricaoPrato, chefeResponsavel);
                        pratos.add(prato);

                        Chefe chefe1 = new Chefe("Cristiano", "Chefe", 28, 4500.0,"Masculino",204.55, 8, "c1");
                        Chefe chefe2 = new Chefe("Gustavo", "Chefe", 36, 4500.0,"Masculino",204.55, 8, "c2");


                        if (prato.getChefeResponsavel().equals("c1")){
                            chefe1.adicionarPrato(prato);
                        } else{
                            chefe2.adicionarPrato(prato);
                        }
                    }
                }
                return pratos;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        for (List<String> linha : tabela){
            System.out.println(linha);
        }
        return pratos;
    }
}
