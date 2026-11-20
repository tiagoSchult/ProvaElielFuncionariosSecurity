
package com.bn.funcionario.services;

import com.bn.funcionario.models.FuncionarioModel;
import com.bn.funcionario.repositories.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioServices {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServices(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Funcionario nao encontrado"
                ));
    }

    public FuncionarioModel criarPessoa(FuncionarioModel funcionarioModel) {
        funcionarioModel.setId(null);
        return funcionarioRepository.save(funcionarioModel);
    }

    public FuncionarioModel atualizar(Long id, FuncionarioModel funcionarioModel) {
        FuncionarioModel funcionario = buscarPorId(id);

        funcionario.setNome(funcionarioModel.getNome());
        funcionario.setCpf(funcionarioModel.getCpf());
        funcionario.setRua(funcionarioModel.getRua());

        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        FuncionarioModel funcionario = buscarPorId(id);
        funcionarioRepository.delete(funcionario);
    }
}
