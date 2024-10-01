package br.ueg.biblioteca.annotation;

import br.ueg.biblioteca.model.Livro;
import br.ueg.biblioteca.service.LivroService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Aspect
@Component
public class EmprestimoAspect  {

    @Autowired
    private LivroService livroService;

    @Before("@annotation(CheckEmprestimo)")
    public void checkBookAvailability(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Long id) {
                Optional<Livro> livroExistente = livroService.findById(id);

                if (livroExistente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro não foi encontrado.")).isEmprestado()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O livro não está disponível para exclusão.");
                }
            }
        }
    }

}