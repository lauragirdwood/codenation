package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	public ArrayList<Time> times = new ArrayList<>();
	public ArrayList<Jogador> jogadores = new ArrayList<>();

	// ok
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
							String corUniformeSecundario) {

		if(times.stream().anyMatch(time -> time.getId().equals(id))) {
			throw new IdentificadorUtilizadoException("Identificador já utilizado");
		}
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	// ok
	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
							   Integer nivelHabilidade, BigDecimal salario) {

		Time time = buscarTimePorId(idTime);
		if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) {
			throw new IdentificadorUtilizadoException("Identificador já utilizado");
		}
		jogadores.add(new Jogador(id, time.getId(), nome, dataNascimento, nivelHabilidade, salario));
	}

	//ok
	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador novoCapitao = buscarJogadorPorId(idJogador);
		Time time = buscarTimePorId(novoCapitao.getIdTime());
		novoCapitao.setCapitao(true);

		Jogador antigoCapitao = time.getCapitao();

		if (antigoCapitao != null) {
			antigoCapitao.setCapitao(false);
		}
		time.setCapitao(novoCapitao);
	}

	//ok
	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Jogador capitao = buscarTimePorId(idTime).getCapitao();
		if (capitao != null) {
			return capitao.getId();
		} else {
			throw new CapitaoNaoInformadoException();
		}
	}

	// ok
	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
			return buscarJogadorPorId(idJogador).getNome();
	}

	// ok
	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
			return buscarTimePorId(idTime).getNome();
	}

	// ok
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = buscarTimePorId(idTime);
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.map(Jogador::getId)
					.collect(Collectors.toList());
	}

	 // ok
	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = buscarTimePorId(idTime);
		return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))	// filtra os jogadores do time específico em questão
					.max(Comparator.comparingInt(Jogador::getNivelHabilidade))	// pega o jogador que tem o maior nivel de habilidade
					.map(Jogador::getId).get();								// retorna o id do jogador encontrado
	}

	// ok
	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = buscarTimePorId(idTime);
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.min(Comparator.comparing(Jogador::getDataNascimento)
							.thenComparing(Jogador::getId))
					.map(Jogador::getId).get();
	}

	// ok
	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
			return times.stream().map(Time::getId).
					collect(Collectors.toList());
	}

	// ok
	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = buscarTimePorId(idTime);
		return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.max(Comparator.comparing(Jogador::getSalario)
					.thenComparing(Jogador::getId)).get().getId();
	}

	// ok
	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscarJogadorPorId(idJogador);
		return jogador.getSalario();
	}

	// ok
	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
			return jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
					.thenComparing(Jogador::getId))
					.limit(top)
					.map(Jogador::getId)
					.collect(Collectors.toList());
	}

	// ok
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		Time timeCasa = buscarTimePorId(timeDaCasa);
		Time timeFora = buscarTimePorId(timeDeFora);

		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())){
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	// ok
	public Jogador buscarJogadorPorId(Long idJogador) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).
				findAny().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
	}

	// ok
	public Time buscarTimePorId(Long idTime) {
		return times.stream().filter(time -> time.getId().equals(idTime)).
				findAny().orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}
}