abstract class Livro {
  public int numPaginas;
  public String nome;
  public String genero; //tipo do livro
  public String autor;
}

class LivroLivraria extends Livro {
  private double valor; //valor do livro
  private int quantidade; //quantidade desse livro

  public LivroLivraria(String nome, String genero, int numPaginas, String autor, double valor, int qtd) {
    if(qtd < 0) {
      System.out.println("Quantidade de livros não pode ser negativa!");
      return;
    }
    this.numPaginas = numPaginas;
    this.nome = nome;
    this.genero = genero;
    this.valor = valor;
    quantidade = qtd;
    this.autor = autor;
  }

  public double getValor() {
    return valor;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public void vender(int qtd) {
    if(quantidade - qtd >= 0) {
      quantidade -= qtd;
      System.out.println("Vendido.");
    } else {
      System.out.println("A venda não pode ser feita.");
    }
  }
}

class LivroBiblioteca extends Livro {
  private boolean podeEmprestar; //se pode emprestar o livro
  private int quantidade; //quantidade desse livro

  public LivroBiblioteca(String nome, String genero, int numPaginas, String autor, int qtd, boolean podeEmprestar) {
    if(qtd < 0) {
      System.out.println("Quantidade de livros não pode ser negativa!");
      return;
    }
    this.numPaginas = numPaginas;
    this.nome = nome;
    this.genero = genero;
    this.autor = autor;
    quantidade = qtd;
    this.podeEmprestar = podeEmprestar;
    // vai que, por algum motivo, n pode emprestar o livro, mesmo que tenha quantidade para isso
    if(quantidade == 0) {
      this.podeEmprestar = false;
    }
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void setPodeEmprestar(boolean podeEmprestar) {
    this.podeEmprestar = podeEmprestar;
  }

  public boolean getPodeEmprestar() {
    return podeEmprestar;
  } 

  public void emprestar(int qtd) {
    if(podeEmprestar && quantidade - qtd >= 0) {
      quantidade -= qtd;
      System.out.println("Emprestimo realizado com sucesso.");
    } else {
      System.out.println("O emprestimo não pode ser feito.");
    }
  }

  public void devolverLivro(int qtd){
    quantidade += qtd;
    System.out.println("Livro(s) devolvido com sucesso.");
  }

}

public class Q1 {
  public static void main(String[] args) {
    LivroLivraria livroL = new LivroLivraria("dale", "terror", 230, "Guilherme", 45.99, 3);
    livroL.vender(2);
    livroL.vender(3);

    LivroBiblioteca livroB = new LivroBiblioteca("Teste1", "Ficção", 150, "Gabriel", 2, false);
    livroB.emprestar(2);
    livroB.setPodeEmprestar(true);
    livroB.emprestar(2);
    livroB.emprestar(5);
    livroB.devolverLivro(1);
    livroB.emprestar(1);
  }
}
