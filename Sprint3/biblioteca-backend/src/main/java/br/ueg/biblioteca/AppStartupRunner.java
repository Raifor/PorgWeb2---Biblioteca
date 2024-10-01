package br.ueg.biblioteca;

import br.ueg.biblioteca.model.Livro;
import br.ueg.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    LivroService livroService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initiateDemoInstance();
    }

    private void initiateDemoInstance() {
        Livro livro = new Livro();
        livro.setTitulo("Livro A");
        livro.setAutor("Eu");
//        livro.setDataEmprestimo(new Date());
//        livro.setDataDevolucao();
//        livro.setEmprestado(true);

        livroService.save(livro);
    }
}