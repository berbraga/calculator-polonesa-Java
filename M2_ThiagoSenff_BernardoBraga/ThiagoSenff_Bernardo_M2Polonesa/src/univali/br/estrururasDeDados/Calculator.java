package univali.br.estrururasDeDados;

import univali.br.estrururasDeDados.fila.Fila;
import univali.br.estrururasDeDados.pilha.Pilha;

import static java.lang.StrictMath.pow;

public class Calculator {

    public Calculator(){}

    public Fila<String> transform(Fila<String> inFix){

        Fila<String> posFix = new Fila<>();
        Pilha<String> trab = new Pilha<>();
        trab.add("(");
        String x,y;
        int conter = 0;
        do {
            conter++;

            x = inFix.get(conter);

            if (priority(x)==6){
                posFix.add(x);
            }
            else {
                if (x.equals("(")){
                    trab.add(x);
                }
                else{
                    if (x.equals(")") || x.equals("#")){
                        do {
                            y=trab.remove();
                            if ((!y.equals("("))){
                                posFix.add(y);
                            }
                        }while (!y.equals("("));
                    }
                    else {
                        if(!trab.isEmpty()){
                            while(priority(x) < priority(trab.getTop())){
                                y= trab.remove();
                                posFix.add(y);
                            }
                        }
                        trab.add(x);
                    }
                }
            }
        }while (!x.equals("#"));

        return posFix;
    }

    public double resolve(Fila<String> posFix){
        Pilha<String> constantes = new Pilha<>();
        String temp;
        double a,b;
        for (int i = 1; i <= posFix.getSize(); i++) {
            temp = posFix.get(i);
            switch (temp){
                case "+":
                    a = Double.parseDouble(constantes.remove());
                    b = Double.parseDouble(constantes.remove());
                    constantes.add(Double.toString(a+b));
                    break;
                case "-":
                    a = Double.parseDouble(constantes.remove());
                    b = Double.parseDouble(constantes.remove());
                    constantes.add(Double.toString(b-a));
                    break;
                case "*":
                    a = Double.parseDouble(constantes.remove());
                    b = Double.parseDouble(constantes.remove());
                    constantes.add(Double.toString(b*a));
                    break;
                case "/":
                    a = Double.parseDouble(constantes.remove());
                    b = Double.parseDouble(constantes.remove());
                    constantes.add(Double.toString(b/a));
                    break;
                case "^":
                    a = Double.parseDouble(constantes.remove());
                    b = Double.parseDouble(constantes.remove());
                    constantes.add(Double.toString(pow(b,a)));
                    break;
                default:
                    constantes.add(temp);
                    break;

            }
        }
        return Double.parseDouble(constantes.get(0));
    }



    public boolean validation(String equacao){

        equacao = equacao.toUpperCase();

        if(equacao.equals("D")){


            return true;

        }

        if(equacao.equals("#")){
            return true;

        }
        if(equacao.length() == 1 && priority(equacao) < 6){
            return true;
        }
        // se o usuario digitar mesmo tipo de operador , imprime uma mensagem de erro
        if( isNumber(equacao) ){
            return true;
        }
        System.out.println("entrada invalida , digite novamente:\n");
        return false;
    }

    public boolean isNumber(String s){
        try {
            Double.parseDouble(s);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    public double potencia( double b, double a ){
        double resultado = b;
        int i = 0;
        do{
            resultado *= b;
            i++;
        }while(i==a);
        return resultado;
    }

    private int priority(String c){
        if (c.equals("#"))return 1;
        if (c.equals("(") || c.equals(")"))return 2;
        if (c.equals("+") || c.equals("-"))return 3;
        if (c.equals("*") || c.equals("/"))return 4;
        if(c.equals("^") )return 5;
        return 6;
    }

    public boolean equationIsValid(Fila<String> inFix) {
        if(inFix.isEmpty())return false;

        String current = "";
        String next = "";

        int cont = 0;
        for (int i = 1; i <= inFix.getSize()-1; i++) {
            current = inFix.get(i);
            if( current.equals("(") )cont++;
            else{
                if( current.equals(")") )cont--;
                else{
                    next = inFix.get(i+1);
                    if( isNumber(next)== isNumber(current) && !"()".contains(next) ){
                        return false;
                    }
                }
            }
        }

        return cont == 0;
    }
}
