
package dasafioparser;

import java.io.*;
import java.util.*;

public class DasafioParser {
    
    static int linhaAtual = 0;
    static int linhasTotais;
    
    public static void main(String[] args) throws IOException {
        
        
        FileInputStream arquivo = new FileInputStream("arquivo.xml");
        FileInputStream arquivoParaConta = new FileInputStream("arquivo.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(arquivo));
        BufferedReader aux = new BufferedReader(new InputStreamReader(arquivoParaConta));
        FileWriter escrever = new FileWriter("arquivo.json");
        String linha;
        
        while ((linha = aux.readLine()) != null){
            linhasTotais++;
        }
        //System.out.println(linhasTotais);
        
        
        while ((linha = br.readLine()) != null){
            escrever.write(semNome(linha));
            linhaAtual++;
        }
        escrever.close();
    }
          
    
    public static String semNome(String linha){
        String array[] = linha.split("</");
        //String retorno;
        //Map<String, String> linhaJson = new HashMap<String,String>();
        String nomeVariavel = "";
        String conteudo = "";
        String lixo = "";
        boolean confirma = false;
        
        
        for(int i = 0; i < array[0].length();i++){
            
            if(array[0].charAt(i) == '<' && !confirma){
                lixo += array[0].charAt(i);
            } else if(array[0].charAt(i) != '>' && !confirma){
                nomeVariavel += array[0].charAt(i);
            } else if (array[0].charAt(i) == '>' && !confirma){
                lixo += array[0].charAt(i);
                confirma = true;
            } else {
                conteudo += array[0].charAt(i);
            }
        }
       if(linhaAtual == 0){
           return ("{\n\"" + nomeVariavel + "\":{");
        } else if (linhaAtual == linhasTotais - 1){
           return ("} \n}");
        } else if(nomeVariavel == ""){
            return ("},");
            //System.out.println("{");
        } else if (conteudo == ""){
            return ("{");
        } else {
            return ("\"" + nomeVariavel + "\": \"" + conteudo + "\",");
        }
    }    
}
