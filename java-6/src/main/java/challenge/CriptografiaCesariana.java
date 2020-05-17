package challenge;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (!texto.isEmpty()){
            String textoMinusculo = texto.toLowerCase();
            char[] charsCripto = new char[textoMinusculo.length()];

            for(int i = 0; i < texto.length(); i++){
                char c = textoMinusculo.charAt(i);
                // se for número ou espaço
                if(c >= 48 && c <= 57 || c == 32){
                    charsCripto[i] = c;
                    // caso contrario, se for letra do alfabeto minusculo
                } else if (c >= 97 && c <= 122) {
                    charsCripto[i] = (char) (c + 3);
                }
            }
                return new String(charsCripto);
            } else{
            throw new IllegalArgumentException("String passada como parametro está vazia");
            }
    }

    @Override
    public String descriptografar(String texto) {
        if (!texto.isEmpty()) {
            String textoMinusculo = texto.toLowerCase();
            char[] charsDescripto = new char[textoMinusculo.length()];

            for (int i = 0; i < texto.length(); i++) {
                char c = textoMinusculo.charAt(i);
                // se for número ou espaço
                if (c >= 48 && c <= 57 || c == 32) {
                    charsDescripto[i] = c;
                    // caso contrario, se for letra do alfabeto minusculo
                } else if (c >= 97 && c <= 122) {
                    charsDescripto[i] = (char) (c - 3);
                }
            }
            return new String(charsDescripto);
        } else {
            throw new IllegalArgumentException("String passada como parametro está vazia");
        }
    }
}
