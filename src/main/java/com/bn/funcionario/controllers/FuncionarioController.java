
package com.bn.funcionario.controllers;

import com.bn.funcionario.models.FuncionarioModel;
import com.bn.funcionario.services.FuncionarioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioServices funcionarioServices;

    public FuncionarioController(FuncionarioServices funcionarioServices) {
        this.funcionarioServices = funcionarioServices;
    }

    @GetMapping
    public List<FuncionarioModel> findAll() {
        return funcionarioServices.findAll();
    }

    @GetMapping("/{id}")
    public FuncionarioModel buscarPorId(@PathVariable Long id) {
        return funcionarioServices.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<FuncionarioModel> criarFuncionario(
            @RequestBody FuncionarioModel funcionarioModel) {

        FuncionarioModel funcionario = funcionarioServices.criarPessoa(funcionarioModel);

        URI endereco = URI.create("/api/funcionarios/" + funcionario.getId());

        return ResponseEntity.created(endereco).body(funcionario);
    }

    @PutMapping("/{id}")
    public FuncionarioModel atualizar(
            @PathVariable Long id,
            @RequestBody FuncionarioModel funcionarioModel) {

        return funcionarioServices.atualizar(id, funcionarioModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
