class Texto {
  private int tamanho;
  private String str;

  public Texto(String str) {
    // remover os espaços em branco no começo e no final
    this.str = str.trim();
    tamanho = str.length();
  }

  public void imprimirTexto() {
    System.out.println(str);
  }

  public int tamanhoTexto() {
    return tamanho;
  }

  public int qtdPalavras() {
    int result = 0;
    boolean eraEspacoAntes = true; //qualquer coisa que não seja letra ou digito na verdade
    for(int i = 0; i < str.length(); i++) {
      if(eraEspacoAntes && (Character.isLetterOrDigit(str.charAt(i)))){
        eraEspacoAntes = false;
        result += 1;
      } else if(!eraEspacoAntes && !(Character.isLetterOrDigit(str.charAt(i)))) {
        eraEspacoAntes = true;
      }
    }

    return result;
  }

  // aqui e na função de substituir n vamos considerar palavras minusculas e maiusculas
  // "ABC" == "abc"
  public int contaFreq(String s) {
    final int tamSubstr = s.length();
    String aux = this.str.toLowerCase();
    s = s.toLowerCase();
    int result = 0;

    for(int i = 0; i < tamanho - tamSubstr + 1; i++){
      int j = 0;
      while(j < tamSubstr){
        if (aux.charAt(i + j) != s.charAt(j)){
          break;
        }
        j += 1;
      }
      if(j == tamSubstr) {
        result += 1;
      }
    }

    return result;
  }

  public void substituirOcorrencia(String substituida, String vaiSubstituir){
    this.str = str.toLowerCase().replaceAll(substituida.toLowerCase(), vaiSubstituir);
  }
}

public class Q2 {
  public static void main(String[] args) {
    Texto text = new Texto(" Porta   portaria oi oi o    i  ; dale dele dele doli;23 45");
    System.out.println(text.contaFreq("porta"));
    System.out.println(text.qtdPalavras());
    text.substituirOcorrencia("porta", "Dale");
    text.imprimirTexto();
  }
}
