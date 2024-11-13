import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GerenciarDados {
    private List<Prato> pratos = new ArrayList<>();
    private List<Chefe> chefes = new ArrayList<>();
    private List<Garcom> garcons = new ArrayList<>();

    public GerenciarDados() {
        Chefe c1 = new Chefe("Cristiano", "Chefe", 28, 4500.00, "Masculino", 204.55, 8);
        Chefe c2 = new Chefe("Gustavo", "Chefe", 36, 4500.00, "Masculino", 204.55, 8);
        chefes.add(c1);
        chefes.add(c2);

        Garcom g1 = new Garcom("Marcos", "Garçom", 22, 2000.0, "Masculino", 100.0, 8, 20);
        Garcom g2 = new Garcom("Ana", "Garçonete", 30, 2200.0, "Feminino", 110.0, 8, 22);
        garcons.add(g1);
        garcons.add(g2);
    }

    public void adicionarChefe(Chefe chefe) {
        chefes.add(chefe);
    }

    public List<Chefe> getChefes() {
        return chefes;
    }

    public List<Garcom> getGarcons() {
        return garcons;
    }

    public List<Prato> lerArquivo() throws Erro {
        String pratosArquivo = "pratos.csv";
        String separador = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(pratosArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosPratos = linha.split(separador);
                if (dadosPratos.length < 3) {
                    throw new Erro("Dados incompletos");
                }

                String nomePrato = dadosPratos[0];
                double valorPrato = Double.parseDouble(dadosPratos[1]);
                String descricaoPrato = dadosPratos[2];

                Prato prato = new Prato(nomePrato, valorPrato, descricaoPrato);
                pratos.add(prato);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new Erro("Não foi possível acessar o índice no arquivo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pratos;
    }
}
