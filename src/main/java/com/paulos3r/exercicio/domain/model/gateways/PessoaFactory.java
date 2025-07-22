package com.paulos3r.exercicio.domain.model.gateways;

import com.paulos3r.exercicio.domain.model.Pessoa;
import com.paulos3r.exercicio.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PessoaFactory {

    /**
     *  Criar uma instância de Pessoa
     * @param nome
     * @param cpf
     * @param data_nascimento
     * @param endereco
     * @param telefone
     * @param usuario
     * @return instancia de Pessoass
     *
     * @throws IllegalArgumentException se os parâmetros informados não forem validos
     */
    public Pessoa createPessoa(String nome, String cpf, LocalDate data_nascimento, String endereco, String telefone, Usuario usuario){

        if (cpf.isEmpty()){
            throw new IllegalArgumentException("Cpf não pode ser nulo!");
        }
        if (nome.isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Cpf não esta no formato ###########! ");
        }
        if (usuario == null){
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        }
        if (data_nascimento == null ){
            throw new IllegalArgumentException("Data de nescimento não pode ser nulo");
        }
        if (LocalDate.now().isBefore(data_nascimento)){
            throw new IllegalArgumentException("data de nescimento tem que ser no passado!");
        }
        return new Pessoa(nome, cpf, data_nascimento, endereco, telefone,usuario);
    }
}