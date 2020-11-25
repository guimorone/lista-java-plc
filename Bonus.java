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

    if(expr.contains("+")) {
      String[] soma = expr.split("[+]"); // tem que usar isso pq '*' é um caractere especial
      String valor = "(";
      for(String i : soma) {
        valor += imprimirArvore(i);
        valor += '+';
      }
      valor = valor.substring(0, valor.length() - 1); // para tirar o ultimo operador a mais
      return valor + ")";
    } else if(expr.contains("-")) {
      String[] sub = expr.split("-");
      String valor = "(";
      for(String i : sub) {
        valor += imprimirArvore(i);
        valor += '-';
      }
      valor = valor.substring(0, valor.length() - 1);
      return valor + ")";
    } else if(expr.contains("*")){
      String[] mult = expr.split("[*]");
      String valor = "(";
      for(String i : mult) {
        valor += imprimirArvore(i);
        valor += '*';
      }
      valor = valor.substring(0, valor.length() - 1);
      return valor + ")";
    } else if(expr.contains("/")) {
      String[] div = expr.split("[/]");
      String valor = "(";
      for(String i : div) {
        valor += imprimirArvore(i);
        valor += '/';
      }
      valor = valor.substring(0, valor.length() - 1);
      return valor + ")";
    } else {
      return "(" + expr + ")";
    }
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

class ExprOpTernario implements Expression {
  protected String expression;

  public boolean checaExpr(String expr) {
    final int len = expr.length();
    for(int i = 0; i < len; i++) {
      if(expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '=') {
        return false;
      }
    }

    return true;
  }

  public String avaliar(String expr) {
    String[] fstDiv = expr.split("[?]"); // regex
    String[] sndDiv = fstDiv[1].split(":");
    ExprLogica auxLogic = new ExprLogica(fstDiv[0]);
    final boolean result = Boolean.parseBoolean(auxLogic.avaliar(fstDiv[0]));
    if(result) {
      // true == aritmetica, false == logica
      if(checaExpr(sndDiv[0])) {
        ExprAritmetica aux = new ExprAritmetica(sndDiv[0]);
        return aux.avaliar(sndDiv[0]);
      } else {
        ExprLogica aux = new ExprLogica(sndDiv[0]);
        return aux.avaliar(sndDiv[0]);
      }
    } else {
      if(checaExpr(sndDiv[1])) {
        ExprAritmetica aux = new ExprAritmetica(sndDiv[1]);
        return aux.avaliar(sndDiv[1]);
      } else {
        ExprLogica aux = new ExprLogica(sndDiv[1]);
        return aux.avaliar(sndDiv[1]);
      }
    }
  }

  public String imprimirArvore(String expr) {
    return expr;
  }

  public ExprOpTernario(String expr) {
    expression = expr;
  }
}

public class Bonus {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    //teste
    final String teste = scan.nextLine();
    ExprOpTernario eot = new ExprOpTernario(teste);
    System.out.println(eot.avaliar(teste));
    System.out.println(eot.imprimirArvore(teste));
    scan.close();
  }
}