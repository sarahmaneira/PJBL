import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GerenciarDados {
    private List<Prato> pratos = new ArrayList<>();

    public List<Prato> lerArquivo() {
        String pratosArquivo = "pratos.csv";
        String separador = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(pratosArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosPratos = linha.split(separador);

                String nomePrato = dadosPratos[0];
                double valorPrato = Double.parseDouble(dadosPratos[1]);
                String descricaoPrato = dadosPratos[2];
                String chefeResponsavel = dadosPratos[3];

                Prato prato = new Prato(nomePrato, valorPrato, descricaoPrato, chefeResponsavel);
                pratos.add(prato);

                // Gerencia os chefes fora do loop, apenas um por prato
                Chefe chefe1 = new Chefe("Cristiano", "Chefe", 28, 4500.0, "Masculino", 204.55, 8, "c1");
                Chefe chefe2 = new Chefe("Gustavo", "Chefe", 36, 4500.0, "Masculino", 204.55, 8, "c2");

                if (prato.getChefeResponsavel().equals("c1")) {
                    chefe1.adicionarPrato(prato);
                } else {
                    chefe2.adicionarPrato(prato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pratos;
    }
}
