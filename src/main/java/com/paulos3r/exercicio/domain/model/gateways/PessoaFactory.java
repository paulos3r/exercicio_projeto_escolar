package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;

import java.time.LocalDate;

public class PessoaFactory {

    public Pessoa createPessoa(String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone){
        return new Pessoa(nome, cpf, data_nascimento, endereco, telefone);
    }

    public Pessoa updatePessoa(Long id, String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone, Usuario usuario){
        return new Pessoa(id, nome, cpf, data_nascimento, endereco, telefone, usuario);
    }
}