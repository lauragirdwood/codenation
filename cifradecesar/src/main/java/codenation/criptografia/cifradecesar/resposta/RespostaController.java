package codenation.criptografia.cifradecesar.resposta;

import codenation.criptografia.cifradecesar.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

@RestController
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/respostas")
    public Resposta save(@RequestBody Resposta resposta) {
        // 'INSERT INTO resposta ...'
        return respostaRepository.save(resposta);
    }

    // READ (R DO CRUD) -- GET do HTTP
    @GetMapping("/respostas")
    public List<Resposta> findAll(){
        // 'SELECT * FROM resposta'
        return respostaRepository.findAll();
    }

    // UPDATE (U DO CRUD) -- PUT do HTTP
    @PutMapping("/respostas/{id}")
    public Resposta update(@PathVariable Long id, @RequestBody Resposta resposta)
            throws ResourceNotFoundException {
        // 'UPDATE resposta SET ... WHERE ...'
        return respostaRepository.findById(id).map(respostaAtualizada -> {
            respostaAtualizada.setNumero_casas(resposta.getNumero_casas());
            respostaAtualizada.setToken(resposta.getToken());
            respostaAtualizada.setCifrado(resposta.getCifrado());
            respostaAtualizada.setDecifrado(resposta.getDecifrado());
            respostaAtualizada.setResumo_criptografico(resposta.getResumo_criptografico());
            return respostaRepository.save(respostaAtualizada);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe resposta cadastrada com o id: " + id));
    }

    // DELETE (D DO CRUD)
    @DeleteMapping("/respostas/{id}")
    public void delete(@PathVariable Long id){
        respostaRepository.deleteById(id);
    }



}
