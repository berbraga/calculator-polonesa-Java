package univali.br;

import univali.br.estrururasDeDados.Calculator;
import univali.br.estrururasDeDados.fila.Fila;
import univali.br.estrururasDeDados.pilha.Pilha;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Calculator test= new Calculator();
        Fila<String> inFix= new Fila<>();
        Pilha<String> inFixWriter = new Pilha<>();
        Scanner ler = new Scanner(System.in);
        String entrada="";
        boolean continuar= true;
        do{
            do{
                System.out.println("============================\n"+inFixWriter);
                System.out.println("Para Finalizar a equacao digite: # ");
                System.out.println("Para Deletar o ultimo elemento adicionado digite: D ");
                System.out.println("Digite: " );
                entrada =  ler.next();

            }while(!test.validation(entrada));

            if (entrada.toUpperCase().equals("D")){
                if (!inFixWriter.isEmpty())inFixWriter.remove();
            }
            else{
                inFixWriter.add(entrada);
            }

            if(entrada.equals("#")) {

                if ( !test.equationIsValid( inFix = inFixWriter.toFila())){
                    System.out.println("A equação " + inFix+"\nNão é valida, tente novamente" );
                    inFixWriter = new Pilha<String>();

                }else{
                    continuar = false;
                }
            }
        }while(continuar);

        Fila<String> posFix= test.transform(inFix);
        double restultado = test.resolve(posFix);
        System.out.println(restultado);
        System.out.println(posFix);
        System.out.println(inFix);
    }
}