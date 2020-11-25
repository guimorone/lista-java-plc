import java.util.Scanner;

interface Expression {
  String avaliar(String expr);
  String imprimirArvore(String expr);
}

// (/) > (*) > (-) > (+)
class ExprAritmetica implements Expression {
  protected String expression;
  private double contaValor = 0; // resultado da expressão em avaliar

  public String avaliar(String expr) {
    
    if(expr.contains("+")) {
      String[] soma = expr.split("[+]");
      double valor = Double.parseDouble(avaliar(soma[0]));
      final int len = soma.length;
      for(int i = 1; i < len; i++) {
        valor += Double.parseDouble(avaliar(soma[i]));
      }
      contaValor = valor;
    } else if(expr.contains("-")) {
      String[] sub = expr.split("-");
      double valor = Double.parseDouble(avaliar(sub[0]));
      final int len = sub.length;
      for(int i = 1; i < len; i++) {
        valor -= Double.parseDouble(avaliar(sub[i]));
      }
      contaValor = valor;
    } else if(expr.contains("*")){
      String[] mult = expr.split("[*]");
      double valor = Double.parseDouble(avaliar(mult[0]));
      final int len = mult.length;
      for(int i = 1; i < len; i++) {
        valor *= Double.parseDouble(avaliar(mult[i]));
      }
      contaValor = valor;
    } else if(expr.contains("/")) {
      String[] div = expr.split("[/]");
      double valor = Double.parseDouble(avaliar(div[0]));
      final int len = div.length;
      for(int i = 1; i < len; i++) {
        valor /= Double.parseDouble(avaliar(div[i]));
      }
      contaValor = valor;
    } else {
      return expr;
    }
    
    return Double.toString(contaValor);
  }

  public String getExpression() {
    return expression;
  }

  public String imprimirArvore(String expr) {
    int len = expr.length();
    for(int i = 0; i < len; i++){
      if(expr.charAt(i) == '+') {
        return "(" + imprimirArvore(expr.substring(0, i)) + "+" + imprimirArvore(expr.substring(i + 1, len)) + ")";
      }
    }

    for(int i = 0; i < len; i++){
      if(expr.charAt(i) == '-') {
        return "(" + imprimirArvore(expr.substring(0, i)) + "-" + imprimirArvore(expr.substring(i + 1, len)) + ")";
      }
    }

    for(int i = 0; i < len; i++){
      if(expr.charAt(i) == '*') {
        return "(" + imprimirArvore(expr.substring(0, i)) + "*" + imprimirArvore(expr.substring(i + 1, len)) + ")";
      }
    }

    for(int i = 0; i < len; i++){
      if(expr.charAt(i) == '/') {
        return "(" + imprimirArvore(expr.substring(0, i)) + "/" + imprimirArvore(expr.substring(i + 1, len)) + ")";
      }
    }
    return "(" + expr + ")";
  }

  public ExprAritmetica(String expr) {
    expression = expr;
  }
}

class ExprLogica implements Expression {
  protected String expression;
  private ExprAritmetica[] exs = new ExprAritmetica[2]; // para dividir as duas expressões aritmeticas entre o operador logico

  public String avaliar(String expr) {
    String aux = "";
    final int len = expr.length();
    for(int i = 0; i < len; i++) {
      if(expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '=' || expr.charAt(i) == '!') {
        aux += expr.charAt(i);
      }
    }

    String[] aux2 = expr.split(aux);
    exs[0] = new ExprAritmetica(aux2[0]);
    exs[1] = new ExprAritmetica(aux2[1]);

    String resultado1 = exs[0].avaliar(exs[0].getExpression());
    String resultado2 = exs[1].avaliar(exs[1].getExpression());

    if(aux.length() == 1) {
      if(aux.charAt(0) == '>'){
        return Boolean.toString(Double.parseDouble(resultado1) > Double.parseDouble(resultado2));
      } else {
        return Boolean.toString(Double.parseDouble(resultado1) < Double.parseDouble(resultado2));
      }
    } else {
      if(aux.charAt(0) == '>'){
        return Boolean.toString(Double.parseDouble(resultado1) >= Double.parseDouble(resultado2));
      } else if(aux.charAt(0) == '<') {
        return Boolean.toString(Double.parseDouble(resultado1) <= Double.parseDouble(resultado2));
      } else if(aux.charAt(0) == '!') {
        return Boolean.toString(Double.parseDouble(resultado1) != Double.parseDouble(resultado2));
      } else {
        return Boolean.toString(Double.parseDouble(resultado1) == Double.parseDouble(resultado2));
      }
    }
  }

  public String imprimirArvore(String expr) {
    String aux = "";
    final int len = expr.length();
    for(int i = 0; i < len; i++) {
      if(expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '=' || expr.charAt(i) == '!') {
        aux += expr.charAt(i);
      }
    }

    String[] aux2 = expr.split(aux);
    exs[0] = new ExprAritmetica(aux2[0]);
    exs[1] = new ExprAritmetica(aux2[1]);

    String resultado1 = exs[0].imprimirArvore(exs[0].getExpression());
    String resultado2 = exs[1].imprimirArvore(exs[1].getExpression());

    return "(" + resultado1 + aux + resultado2 + ")";
  }

  public ExprLogica(String expr) {
    expression = expr;
  }
  
}

public class Q6 {

  public boolean descobreTipoExpr(String expr){
    final int len = expr.length();
    for(int i = 0; i < len; i++) {
      if(expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '=') {
        return false;
      }
    }

    return true;
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    //teste
    Q6 aux = new Q6();
    final String expr1 = "2*3+4/6/6*4";
    final String expr2 = "2+5/10<12+16";
    // true == Aritmetico, false == Logico
    final boolean tipoExpr1 = aux.descobreTipoExpr(expr1);
    final boolean tipoExpr2 = aux.descobreTipoExpr(expr2);

    if(tipoExpr1) {
      ExprAritmetica x = new ExprAritmetica(expr1);
      System.out.println("ao chamar avaliar: " + x.avaliar(expr1));
      System.out.println("ao chamar imprimirArvore: " + x.imprimirArvore(expr1));
    } else {
      ExprLogica x = new ExprLogica(expr1);
      System.out.println("ao chamar avaliar: " + x.avaliar(expr1));
      System.out.println("ao chamar imprimirArvore: " + x.imprimirArvore(expr1));
    }

    if(tipoExpr2) {
      ExprAritmetica y = new ExprAritmetica(expr2);
      System.out.println("ao chamar avaliar: " + y.avaliar(expr2));
      System.out.println("ao chamar imprimirArvore: " + y.imprimirArvore(expr2));
    } else {
      ExprLogica y = new ExprLogica(expr2);
      System.out.println("ao chamar avaliar: " + y.avaliar(expr2));
      System.out.println("ao chamar imprimirArvore: " + y.imprimirArvore(expr2));
    }

    String teste = scan.nextLine();
    final boolean testaEntrada = aux.descobreTipoExpr(teste);
    if(testaEntrada) {
      ExprAritmetica x = new ExprAritmetica(teste);
      System.out.println("ao chamar avaliar: " + x.avaliar(teste));
      System.out.println("ao chamar imprimirArvore: " + x.imprimirArvore(teste));
    } else {
      ExprLogica x = new ExprLogica(teste);
      System.out.println("ao chamar avaliar: " + x.avaliar(teste));
      System.out.println("ao chamar imprimirArvore: " + x.imprimirArvore(teste));
    }

    scan.close();
  }
}
