import java.util.ArrayList;
import java.io.*;
import java.util.*;

class PJBL {
    public static void main(String[] args) throws IOException {
        GerenciarDados gerencia = new GerenciarDados();
        try {
            gerencia.lerArquivo();
        } catch (Erro e) {
            System.err.println("Erro ao processar o arquivo");
        }
    }
}
