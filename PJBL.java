import java.util.ArrayList;
import java.io.*;
import java.util.*;

class PJBL {
    public static void main(String[] args) throws IOException {
        // lendo arquivo de pratos
        String pratosArquivo = "pratos.csv";
        String separador = ";";

        List<List<String>> tabela = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pratosArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosPratos = linha.split(separador);
                tabela.add(Arrays.asList(dadosPratos));
                List<String> pratos = Arrays.asList(dadosPratos);

                    for (int i = 0; i < tabela.size(); i++){
                        for (int j = 0; j < pratos.size(); j++) {
                            String nomePrato = tabela.get(i).get(j);
                            double valorPrato = Double.valueOf(tabela.get(i).get(j));
                            String descricaoPrato = tabela.get(i).get(j);
                            String chefeResponsavel = tabela.get(i).get(j);

                            Prato prato = new Prato(nomePrato, valorPrato, descricaoPrato, chefeResponsavel);

                            if (prato.getChefeResponsavel().equals("c1"){
                                chefe1.adicionarPrato(prato);
                            }
                        }
                    }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        for (List<String> linha : tabela){
            System.out.println(linha);
        }
    }
}
