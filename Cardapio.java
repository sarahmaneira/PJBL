import java.util.ArrayList;
import javax.swing.*;

class Cardapio{
    private ArrayList<Prato> pratos = new ArrayList<>();

    public void adicionarPrato(Prato prato){
        pratos.add(prato);
//        System.out.println("Prato adicionado:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição: " + prato.getDescricao());
    }

    public void exibirFormularioAdicionarPrato() {
        JTextField nomeField = new JTextField(10);
        JTextField valorField = new JTextField(10);
        JTextField descricaoField = new JTextField(10);
        JTextField chefeResponsavelField = new JTextField(10);

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(Box.createHorizontalStrut(15));
        formPanel.add(new JLabel("Valor:"));
        formPanel.add(valorField);
        formPanel.add(Box.createHorizontalStrut(15));
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(descricaoField);
        formPanel.add(Box.createHorizontalStrut(15));
        formPanel.add(new JLabel("Chefe Responsável:"));
        formPanel.add(chefeResponsavelField);

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Adicionar Prato", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            double valor = Double.parseDouble(valorField.getText());
            String descricao = descricaoField.getText();
            String chefeResponsavel = chefeResponsavelField.getText();

            Prato prato = new Prato(nome, valor, descricao, chefeResponsavel);
            adicionarPrato(prato);
        }
    }


    public void removerPrato(Prato prato){
        if (pratos.remove(prato)){
            System.out.println("Prato removido:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição" + prato.getDescricao());
        } else {
            System.out.println("Prato não encontrado no cardápio.");
        }
    }

    public void mostrarPratos(Prato prato){
        System.out.println("Pratos no cardápio:");
        for (Prato pratos : pratos){
            System.out.println("Prato:" + prato.getNome() + "- Valor R$:" + prato.getValor() + "- Descrição: " + prato.getDescricao());
        }
    }
}