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

		if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) {
			throw new IdentificadorUtilizadoException("Identificador já utilizado");
		}
		Time time = buscarEValidarPeloIdSeTimeExiste(idTime);
		jogadores.add(new Jogador(id, time.getId(), nome, dataNascimento, nivelHabilidade, salario));
	}

	//ok
	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogadorCapitao = buscarEValidarPeloIdSeJogadorExiste(idJogador);
		Time time = buscarEValidarPeloIdSeTimeExiste(jogadorCapitao.getIdTime());
		time.setCapitao(jogadorCapitao.getId());
	}

	//ok
	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if (buscarEValidarPeloIdSeTimeExiste(idTime).getIdJogadorCapitao() != null) {
			return buscarEValidarPeloIdSeTimeExiste(idTime).getIdJogadorCapitao();
		} else {
			throw new CapitaoNaoInformadoException();
		}
	}

	// ok
	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
			return buscarEValidarPeloIdSeJogadorExiste(idJogador).getNome();
	}

	// ok
	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
			return buscarEValidarPeloIdSeTimeExiste(idTime).getNome();
	}

	// ok
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Long idTimeBuscado = buscarEValidarPeloIdSeTimeExiste(idTime).getId();
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTimeBuscado))
					.map(Jogador::getId)
					.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Long idTimeBuscado = buscarEValidarPeloIdSeTimeExiste(idTime).getId();
		return jogadores.stream()
					.filter(jogador -> jogador.getIdTime()
							.equals(idTimeBuscado))
					.max(Comparator.comparingInt(Jogador::getNivelHabilidade))
					.map(Jogador::getId).get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Long idTimeBuscado = buscarEValidarPeloIdSeTimeExiste(idTime).getId();
			return jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTimeBuscado))
					.min(Comparator.comparing(Jogador::getDataNascimento)
							.thenComparing(Jogador::getId)).get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream().map(Time::getId)
			.sorted().collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Long idTimeBuscado = buscarEValidarPeloIdSeTimeExiste(idTime).getId();
		return jogadores.stream()
			.filter(jogador -> jogador.getIdTime().equals(idTimeBuscado))
			.min(Comparator.comparing(Jogador::getSalario).reversed()
			.thenComparing(Jogador::getId)).get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscarEValidarPeloIdSeJogadorExiste(idJogador);
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
			return jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
					.thenComparing(Jogador::getId))
					.limit(top)
					.map(Jogador::getId)
					.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		Time timeCasa = buscarEValidarPeloIdSeTimeExiste(timeDaCasa);
		Time timeFora = buscarEValidarPeloIdSeTimeExiste(timeDeFora);

		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())){
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	public Jogador buscarEValidarPeloIdSeJogadorExiste(Long idJogador) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).
				findAny().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
	}

	public Time buscarEValidarPeloIdSeTimeExiste(Long idTime) {
		return times.stream().filter(time -> time.getId().equals(idTime)).
				findAny().orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
	}
}