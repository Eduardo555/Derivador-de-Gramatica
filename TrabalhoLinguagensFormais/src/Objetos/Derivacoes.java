
package Objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Derivacoes {
    
    // colcoar  a saida.
    public ArrayList<String> saida = new ArrayList<String>();
    HashMap<String, String> mapSentencas;
    
    // Derivacao Inicio
    public void realizaDerivacao(HashMap<String, String> sentencas)
    {
        // Pegar Map com sentenca.
        mapSentencas = sentencas;
        // Pilha
        Stack<String> pilha = new Stack<>();
        String SentencaInicial = null;
        
        // Percorre a gramatica (map) para encontrar A Sentenca inicial.
            for(Map.Entry<String, String> valores : mapSentencas.entrySet())
            {
                //pilha[i] = valores.getValue();
                if(valores.getKey().equals("S")){
                    SentencaInicial = quebrarPipes(valores.getValue());
                    if(SentencaInicial.isEmpty()){
                        return;
                    }
                    for(int i=0;i<SentencaInicial.length();i++)
                    {
                        try{
                            String convertidao = String.valueOf(SentencaInicial.charAt(i));
                            pilha.add(convertidao);
                        }
                        catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Dialog",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                
            }
            // Chama funcao que ira derivar depois de encontrar o S (Inicial)
            derivar(pilha);
    }
    
    private void derivar(Stack<String> pilha)
    {
        // Recebe a pilha sempre com a sentenca inicial dentro.
        for(int i=0;i<pilha.size();i++)
        {
            if(verificarTerminal(pilha.get(i).charAt(0)))
            {
                // Nao Terminal
                String retorno = encontraSentecaNaoTerminal(pilha.get(i));
                if(retorno.isEmpty()){
                    continue;
                }
                // Precisa remover antes.
                pilha.remove(i);
                //for(int j=0;j<retorno.length();j++)
                for(int j = retorno.length()-1;j >= 0;j--)
                {
                    //pilha.push(String.valueOf(retorno.charAt(j)));
                    pilha.add(i, String.valueOf(retorno.charAt(j)));
                }
                // recursividade para derivar.
                // desta maneira ficara em loop ate acabar os nao terminais. 
                derivar(pilha);
            }
            else
            {
                // Terminal - coloca na saida e remove da pilha
                if(!pilha.get(i).isEmpty()){
                    saida.add(pilha.get(i));
                    pilha.remove(i);
                    derivar(pilha);
                }
            }
            return;
        }
    }
    
    private String encontraSentecaNaoTerminal(String naoTerminal)
    {
        for(Map.Entry<String, String> valores : mapSentencas.entrySet())
        {
            if(valores.getKey().equals(naoTerminal))
            {
                return quebrarPipes(valores.getValue());
            }
        }
        return "Falha";
    }
    
    // Usado para verificar se e um terminal
    private Boolean verificarTerminal(char valor)
    {
        // vai retornar true caso seja maiscula = terminal
        if(!Character.isDigit(valor)){
            return  Character.toString(valor).toUpperCase().equals(Character.toString(valor)); 
        }
        else{
            return false;
        }
    }
    
    // Usado para quebrar as pipes e posibilitar a escolha para derivacao.
    private String quebrarPipes(String sentensas)
    {
        // Por padrao setamos na primeira opcao.
        int opcaoUsuario = 0;
        String sentenca[];
        //String delimitador = "|";
        sentenca = sentensas.split("\\|");
        if(sentenca.length > 0)
        {
            String opcoesString = "";
            for(int i=0;i<sentenca.length;i++)
            {
                opcoesString += i + " : " + sentenca[i] + " | ";
            }
            
            String op= "";
            while(op.isEmpty() || !ehInteiro(op))
            {
                op = JOptionPane.showInputDialog("Existe mais de uma opção para derivação, informe o numero correspondente:\n" + opcoesString);
                if(op == null){
                    return "";
                }
            }
            if(ehInteiro(op))
            {
                if(op == null || op.isEmpty()){
                    return sentenca[0];
                }
                opcaoUsuario = Integer.parseInt(op);
            }
        }  
        else
        {
            opcaoUsuario = 0;
        }
        return sentenca[opcaoUsuario];
        
    }
    
    private boolean ehInteiro( String s ) 
    {  
	// cria um array de char  
        // char[] c = s.toCharArray();  
        boolean eInteiro = true;  
        if(s == null || s.isEmpty()){
            return eInteiro;
        }
        for ( int i = 0; i < s.length(); i++ ) 
        { 
            // verifica se o char não é um dígito  
            if ( !Character.isDigit( s.charAt(i) ) ) 
            {  
                eInteiro = false;  
                break;  
            }  
        }  
	return eInteiro;  
    }
    
}
