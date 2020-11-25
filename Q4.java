class Senha {
  private String senhaLetra;
  private int senhaNumero;

  protected String getSenhaLetra() {
    return senhaLetra;
  }

  protected int getSenhaNumero() {
    return senhaNumero;
  }

  protected void setSenhaLetra(String senhaLetra) {
    this.senhaLetra = senhaLetra;
  }

  protected void setSenhaNumero(int senhaNumero) {
    this.senhaNumero = senhaNumero;
  }

  public Senha(String senhaLetra, int senhaNumero){
    this.senhaLetra = senhaLetra;
    this.senhaNumero = senhaNumero;
  }
}

class Conta {
  private int id;
  private int agencia;
  protected Senha senha;

  public Conta(int id, int agencia, Senha senha) {
    this.id = id;
    this.agencia = agencia;
    this.senha = senha;
  }

  protected int getId() {
    return id;
  }

  protected int getAgencia() {
    return agencia;
  }

  protected String getSenhaLetra() {
    return senha.getSenhaLetra();
  }

  protected int getSenhaNum() {
    return senha.getSenhaNumero();
  }

  protected void setId(int id) {
    this.id = id;
  }

  protected void setAgencia(int agencia) {
    this.agencia = agencia;
  }
}


public class Q4 {
  public static void main(String[] args) {
    Conta c = new Conta(65484984, 9999, new Senha("A1B2C3", 123456));
    System.out.println("Senha de letras: " + c.senha.getSenhaLetra());
    System.out.println("Numero de identificacao: " + c.getId());
    c.senha.setSenhaLetra("XD75F4");
    System.out.println("Senha de letras modificada: " + c.senha.getSenhaLetra());
  }
}
