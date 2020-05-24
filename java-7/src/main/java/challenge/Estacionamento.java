package challenge;

import java.util.ArrayList;

public class Estacionamento {

    private ArrayList<Carro> vagas = new ArrayList<>();
    private final Integer limiteDeVagas = 10;
    private final Integer maxPontosHabilitacao = 20;
    private final Integer idadeMinima = 18;
    private final Integer idadePrioritaria = 55;

    public void validaEstacionamento(Carro carro) {
        if (carro.getMotorista() == null) {
            throw new EstacionamentoException("Proibida a entrada: carro autônomo");
        }
        if (carro.getMotorista().getPontos() > maxPontosHabilitacao) {
            throw new EstacionamentoException("Proibida a entrada: motorista com mais de 20 pontos na habilitação");
        }
        if (carro.getMotorista().getIdade() < idadeMinima) {
            throw new EstacionamentoException("Proibida a entrada: motorista com menos de 18 anos de idade");
        }
    }

    public void estacionar(Carro carro) throws EstacionamentoException {

        validaEstacionamento(carro);

        if (carrosEstacionados() == limiteDeVagas) {
            Carro carroQueDeveSair = vagas.stream()
                    .filter(carroDeJovem -> carroDeJovem.getMotorista().getIdade() < idadePrioritaria)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Proibida a entrada: todos os carros estacionados pertencem a motoristas com mais de 55 anos"));
            vagas.remove(carroQueDeveSair);
        }
        vagas.add(carro);
    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }

}
