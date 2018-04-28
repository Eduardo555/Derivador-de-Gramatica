package Objetos;
import java.util.HashMap;
import java.util.Map;

public class Gramatica 
{
    // Quando instanciar vai gerar um map novo.
    HashMap<String, String> map = new HashMap<>();
    
    public void limparHash()
    {
        map.clear();
    }
    
    // Mantido Fixos por enquanto.
    // Cria o hash e adiciona os valores ao map mantendo a sentenca fixa.
    // Posteriormente colocar os valores de maneira dinamica com entradas.
    public HashMap<String, String> getSentenca()
    {
        // Retorna Map.
        return map;
    }
    
    // Recebe as sentencas todas juntas.
    // salva com pipes para poder manter a referencia do senentecas - terminal
    public void setSentenca(String Terminal, String Sentencas)
    {
        // adiciona valores no map.
        map.put(Terminal, Sentencas);
    }
    
    // Funcao para exibir gramatica completa.
    public void exibirGramatica(){
        
        HashMap<String, String> mapSentencas = getSentenca();
        
        for(Map.Entry<String, String> valores : mapSentencas.entrySet())
        {
            System.out.println(valores.getKey());
            System.out.println(valores.getValue());
        }
        
    }
    
}
