
package Objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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
            if(verificarTerminal(pilha.get(i)).equals(true))
            {
                // Nao Terminal
                String retorno = encontraSentecaNaoTerminal(pilha.get(i));
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
                saida.add(pilha.get(i));
                pilha.remove(i);
                derivar(pilha);
            }
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
    private Boolean verificarTerminal(String valor)
    {
        // vai retornar true caso seja maiscula = terminal
        return valor.toUpperCase().equals(valor);
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
            }
            if(ehInteiro(op))
            {
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
        boolean d = true;  
        for ( int i = 0; i < s.length(); i++ ) 
        { 
            // verifica se o char não é um dígito  
            if ( !Character.isDigit( s.charAt(i) ) ) 
            {  
                d = false;  
                break;  
            }  
        }  
	return d;  
    }
    
}
