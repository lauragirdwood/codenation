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

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
							String corUniformeSecundario) {

		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

		if (validaTime(id)) {
			throw new IdentificadorUtilizadoException();
		}
		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
							   Integer nivelHabilidade, BigDecimal salario) {

		if(validaTime(idTime)) {
			Time time = buscarTimePorId(idTime);
			if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) {
				throw new IdentificadorUtilizadoException("Identificador já utilizado");
			}
			jogadores.add(new Jogador(id, time.getId(), nome, dataNascimento, nivelHabilidade, salario));
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogadorPorId(idJogador);
		Time time = buscarTimePorId(jogador.getIdTime());

		if(jogador.getIdTime().equals(idJogador)) {
			if (buscarJogadoresDoTime(time.getId()).contains(time.getCapitao())) {
				Long antigoCapitao = time.getCapitao();
				Jogador exCapitao = buscarJogadorPorId(antigoCapitao);
				exCapitao.setCapitao(false);
				time.setCapitao(idJogador);
			} else {
				throw new JogadorNaoEncontradoException();
			}
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if(validaTime(idTime)){
			Time time = buscarTimePorId(idTime);
			if(time.getCapitao() == null){
				throw new CapitaoNaoInformadoException();
			} else {
				return time.getCapitao();
			}
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if(validaJogador(idJogador)){
			Jogador jogador = buscarJogadorPorId(idJogador);
			return jogador.getNome();
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if(validaTime(idTime)){
			Time time = buscarTimePorId(idTime);
			return time.getNome();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (validaTime(idTime)) {
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.map(Jogador::getId)
					.collect(Collectors.toList());
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
			return jogadores.stream().filter(time -> validaTime(idTime))	// faz validação se time existe (caso contrário lança exceção)
					.filter(jogador -> jogador.getIdTime().equals(idTime))	// filtra os jogadores do time específico em questão
					.max(Comparator.comparing(Jogador::getNivelHabilidade))	// pega o jogador que tem o maior nivel de habilidade
					.map(Jogador::getId).get();								// retorna o id do jogador encontrado
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if(validaTime(idTime)){
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.min(Comparator.comparing(Jogador::getDataNascimento)
							.thenComparing(Jogador::getId))
					.map(Jogador::getId).get();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> listaVazia = new ArrayList<>();
		if(times.isEmpty()){
			return listaVazia;
		} else {
			return times.stream()
					.map(Time::getId).sorted()
					.collect(Collectors.toList());
		}
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (validaTime(idTime)) {
			return jogadores.stream()
					.max(Comparator.comparing(Jogador::getSalario)
					.thenComparing(Jogador::getId)).get().getId();
		}else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if(validaJogador(idJogador)) {
			Jogador jogador = buscarJogadorPorId(idJogador);
			return jogador.getSalario();
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		if(buscarTimes().isEmpty()){
			return buscarTimes();
		} else {
			return jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
					.thenComparing(Jogador::getId))
					.limit(top)
					.map(Jogador::getId)
					.collect(Collectors.toList());
		}
	}

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

	public Boolean validaTime(Long idTime) {
		return buscarTimes().contains(idTime);
	}

	public Boolean validaJogador(Long idJogador) {
		return jogadores.contains(buscarJogadorPorId(idJogador));
	}

	public Jogador buscarJogadorPorId(Long idJogador) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).
				findAny().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
	}

	public Time buscarTimePorId(Long idTime) {
		return times.stream().filter(time -> time.getId().equals(idTime)).
				findAny().orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}
}