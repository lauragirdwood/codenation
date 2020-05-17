import challenge.CriptografiaCesariana;

public class Main {
    public static void main(String[] args) {
        CriptografiaCesariana teste = new CriptografiaCesariana();

        System.out.println(teste.criptografar("123abc"));

        System.out.println(teste.descriptografar("123def"));
    }
}
