package br.ueg.biblioteca.service;

import br.ueg.biblioteca.annotation.CheckEmprestimo;
import br.ueg.biblioteca.model.Livro;
import br.ueg.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService extends AbstractService<Livro, Long> {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    protected JpaRepository<Livro, Long> getRepository() {
        return livroRepository;
    }

    @Override
    protected void prepareEdit(Livro livro) {

    }

    @Override
    protected void prepareSave(Livro livro) {

    }
}

