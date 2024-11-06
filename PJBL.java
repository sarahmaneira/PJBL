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

                if(Prato.get(Prato.size() -1). equals("c1")){
                    pratosChef1.add(prato);
                } else if (prato.get(prato.size()-1).equals("c2")){
                    pratosChef2.add(prato);
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
