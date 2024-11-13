import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GerenciarDados {
    private List<Prato> pratos = new ArrayList<>();
    private List<Chefe> chefes = new ArrayList<>();
    private Cliente cliente;

    Chefe c1 = new Chefe("Cristiano", "Chefe", 28, 4500.0, "Masculino", 204.55, 8);
    Chefe c2 = new Chefe("Gustavo", "Chefe", 36, 4500.0, "Masculino", 204.55, 8);
    Cliente cliente1 = new Cliente();

    public void adicionarPedidoAoCliente(String pedido) {
        cliente.fazerPedido(pedido);
    }

    public void adicionarChefe(Chefe chefe) {
        chefes.add(chefe);
    }

    public List<Chefe> getChefes() {
        return chefes;
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
            throw new Erro("Não foi possível acessar o indice no arquivo: "+ e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return pratos;
    }
}
