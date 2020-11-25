abstract class Pessoa {
  protected String name;
  protected String cpf;

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public abstract void setName(String name);
  public abstract void setCpf(String cpf);
}

interface Colecao {
  void inserir(Pessoa p);
  int tamanhoColecaoNaoNull();
}

class Professor extends Pessoa {
  protected String funcao;
  protected String area;

  public void setName(String name) {
    this.name = name;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getFuncao() {
    return funcao;
  }

  public String getArea() {
    return area;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Professor(String name, String cpf, String funcao, String area) {
    this.name = name;
    this.cpf = cpf;
    this.funcao = funcao;
    this.area = area;
  }
}

class Aluno extends Pessoa {
  protected String matricula;
  protected String curso;

  public void setName(String name) {
    this.name = name;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getMatricula() {
    return matricula;
  }

  public String getCurso() {
    return curso;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  public Aluno(String name, String cpf, String matricula, String curso) {
    this.name = name;
    this.cpf = cpf;
    this.matricula = matricula;
    this.curso = curso;
  }
}

class ColecaoVetor implements Colecao {
  protected Pessoa[] pessoas = new Pessoa[5];
  protected int restantes = 5;

  public int tamanhoColecaoNaoNull() {
    return restantes;
  }

  public String nomePessoa(Pessoa p) {
    return p.name;
  }

  public void inserir(Pessoa p){
    pessoas[5 - restantes] = p;
    restantes--;
    System.out.println(nomePessoa(p) + " registrado(a) com sucesso!");
    System.out.println("Vagas restantes: " + tamanhoColecaoNaoNull());
  }
}

public class Q5 {
  public static void main(String[] args) {
    //teste
    ColecaoVetor col = new ColecaoVetor();
    Aluno a1 = new Aluno("Guilherme", "98494794949-59", "A2B3245", "Engenharia da comp");
    Professor p1 = new Professor("Francisco", "18902391283-76", "Professor", "TI");
    col.inserir(a1);
    col.inserir(p1);
  }
}
