import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.GridLayout;


public class Cardapio {
    private ArrayList<Prato> pratos = new ArrayList<>();

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
    }

    public void exibirFormularioAdicionarPrato() {
        JTextField nomeField = new JTextField(10);
        JTextField valorField = new JTextField(10);
        JTextField descricaoField = new JTextField(10);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(Box.createHorizontalStrut(15));
        formPanel.add(new JLabel("Valor:"));
        formPanel.add(valorField);
        formPanel.add(Box.createHorizontalStrut(15));
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(descricaoField);
        formPanel.add(Box.createHorizontalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Adicionar Prato", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                double valor = Double.parseDouble(valorField.getText());
                String descricao = descricaoField.getText();

                Prato prato = new Prato(nome, valor, descricao);
                adicionarPrato(prato);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void removerPrato(String prato) {
        List<String> pratosAtualizados = new ArrayList<>();
        String pratosArquivo = "pratos.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(pratosArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (!dados[0].equals(prato)) {
                    pratosAtualizados.add(linha);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de pratos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pratosArquivo))) {
            for (String linhaPrato : pratosAtualizados) {
                bw.write(linhaPrato);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo de pratos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarPratos() {
        System.out.println("Pratos no cardápio:");
        for (Prato prato : pratos) {
            System.out.println("Prato: " + prato.getNome() + " - Valor R$: " + prato.getValor() + " - Descrição: " + prato.getDescricao());
        }
    }
}
