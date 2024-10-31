import java.util.ArrayList;
import java.io.*;

class PJBL {
    public static void main(String[] args) throws IOException {
        FileInputStream fileIn = new FileInputStream("pratos.ser");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Prato p = (Prato) objIn.readObject();

        objIn.close();
        fileIn.close();

        String pratosArquivo = "pratos.csv";
        String separador = ";";

        List<List<String>> 
    }
}



















