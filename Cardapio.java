import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.util.List;

class Cardapio {
    private ArrayList<Prato> pratos = new ArrayList<>();

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
        salvarPratoNoArquivo(prato);
    }

    public void exibirFormularioAdicionarPrato() {
        JTextField nomeField = new JTextField(10);
        JTextField valorField = new JTextField(10);
        JTextField descricaoField = new JTextField(10);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Valor:"));
        formPanel.add(valorField);
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(descricaoField);

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Adicionar Prato", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                double valor = Double.parseDouble(valorField.getText());
                String descricao = descricaoField.getText();

                Prato prato = new Prato(nome, valor, descricao);
                adicionarPrato(prato);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: valor inválido. Digite um número para o campo Valor.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void removerPrato(String nomePrato) {
        pratos.removeIf(prato -> prato.getNome().equals(nomePrato));
        atualizarArquivo();
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    private void salvarPratoNoArquivo(Prato prato) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pratos.csv", true))) {
            bw.write(prato.getNome() + ";" + prato.getValor() + ";" + prato.getDescricao() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar prato.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pratos.csv"))) {
            for (Prato prato : pratos) {
                bw.write(prato.getNome() + ";" + prato.getValor() + ";" + prato.getDescricao() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar arquivo do cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregarPratos() {
        pratos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("pratos.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    Prato prato = new Prato(dados[0], Double.parseDouble(dados[1]), dados[2]);
                    pratos.add(prato);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
