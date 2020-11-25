enum Genero{
  CIS, NBI, FLUIDO, TRANS;
}

class Pessoa {
  protected String nome;
  protected int idade;
  protected String grauEscolaridade;
  protected Genero idGenero;
  protected String oriSexual;
  protected String sexoBio;

  public String getNome() {
    return nome;
  }

  public int getIdade() {
    return idade;
  }

  public String getGrauEscolaridade() {
    return grauEscolaridade;
  }

  public Genero getIdGenero() {
    return idGenero;
  }

  public String getOriSexual() {
    return oriSexual;
  }

  public String getSexoBio() {
    return sexoBio;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setGrauEscolaridade(String grauEscolaridade) {
    this.grauEscolaridade = grauEscolaridade;
  }

  public Pessoa() {
    System.out.println("Pessoa criada, porém sem atributos");
  }

  public Pessoa(String nome, int idade, String grau, Genero gen, String oriSexual, String sexoBio) {
    this.nome = nome;
    this.idade = idade;
    grauEscolaridade = grau;
    idGenero = gen;
    this.oriSexual = oriSexual;
    this.sexoBio = sexoBio;
  }

  public void dormir() {
    System.out.println("Dormindo.");
  }

  public void cagar() {
    System.out.println("Cagando.");
  }

  public void comer() {
    System.out.println("Comendo.");
  }

  public void estudar() {
    System.out.println("Estudando.");
  }

  public void viver() {
    System.out.println("Vivendo.");
  }

  public void sobreviver() {
    System.out.println("Sobrevivendo.");
  }

  public void trabalhar() {
    System.out.println("Trabalhando.");
  }
}

public class Q3 {
  public static void main(String[] args) {
    Pessoa pDefault = new Pessoa();
    Pessoa p = new Pessoa("Guilherme", 19, "Ensino superior incompleto", Genero.CIS, "Hetero", "Masculino");
    p.dormir();
    p.comer();
    p.cagar();
    p.estudar();
    pDefault.sobreviver();
    pDefault.trabalhar();
  }
}
