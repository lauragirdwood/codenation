package codenation.criptografia.cifradecesar.resposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero_casas = 2;
    private String token = "97685b5294eb2336b1a259ff6a512bb417d880af";
    private String cifrado = "fgxgnqrgt: cp qticpkuo vjcv vwtpu eqhhgg kpvq eqfg. wpmpqyp";
    private String decifrado = "developer: an organism that turns coffee into code. unknown";
    private String resumo_criptografico = "9c7354ba4d33fcaa91c1050f15214497dca9cb56";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero_casas() {
        return numero_casas;
    }

    public void setNumero_casas(int numero_casas) {
        this.numero_casas = numero_casas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }

    public String getDecifrado() {
        return decifrado;
    }

    public void setDecifrado(String decifrado) {
        this.decifrado = decifrado;
    }

    public String getResumo_criptografico() {
        return resumo_criptografico;
    }

    public void setResumo_criptografico(String resumo_criptografico) {
        this.resumo_criptografico = resumo_criptografico;
    }

    public Resposta(){}

    public Resposta(int numero_casas, String token, String cifrado, String decifrado, String resumo_criptografico) {
        this.numero_casas = numero_casas;
        this.token = token;
        this.cifrado = cifrado;
        this.decifrado = decifrado;
        this.resumo_criptografico = resumo_criptografico;
    }


    public String decodificar(int chave, String textoCodificado){

        StringBuilder texto = new StringBuilder();
        int tamanhoTexto = textoCodificado.length();

        for (int i = 0; i < tamanhoTexto; i++) {

            int letraDecodificadaASCII = ((int) textoCodificado.charAt(i));

            // se for a ou b volta pro z e y respectivamente
            if(letraDecodificadaASCII == 97 || letraDecodificadaASCII == 98){
                letraDecodificadaASCII += 24;
                // caso contrário, decrementa 2 normalmente a partir do c :)
            } else {
                letraDecodificadaASCII -= chave;
            }

            //limita caracteres printaveis da tabela ASCII
            while (letraDecodificadaASCII <= 32){
                letraDecodificadaASCII += 94;
            }

            // se for caractere especial, volta ao caractere original
            if (letraDecodificadaASCII < 65){ //
                letraDecodificadaASCII += chave;
                // se for espaço, continua espaço
            } if(letraDecodificadaASCII == 124){
                letraDecodificadaASCII = 32;
            }

            texto.append((char)letraDecodificadaASCII);
        }
        return texto.toString();
    }


}
