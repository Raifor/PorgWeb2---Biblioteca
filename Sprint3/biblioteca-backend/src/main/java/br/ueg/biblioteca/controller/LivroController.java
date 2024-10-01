package br.ueg.biblioteca.controller;

import br.ueg.biblioteca.annotation.CheckEmprestimo;
import br.ueg.biblioteca.mapper.LivroMapper;
import br.ueg.biblioteca.model.Livro;
import br.ueg.biblioteca.model.dto.LivroDTO;
import br.ueg.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @Autowired
    private LivroMapper mapper;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> getAll() {
        List<Livro> livros = service.findAll();
        return new ResponseEntity<>(mapper.toDTOList(livros), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable Long id) {
        Optional<Livro> livro = service.findById(id);
        return livro.map(value -> new ResponseEntity<>(mapper.toDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<LivroDTO> createOrUpdate(@RequestBody Livro livro) {
        Livro novoLivro = service.save(livro);
        return new ResponseEntity<>(mapper.toDTO(novoLivro), HttpStatus.CREATED);
    }

    @CheckEmprestimo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Livro> livroExistente = service.findById(id);

        if (livroExistente.isPresent()) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/emprestar")
    public ResponseEntity<LivroDTO> emprestar(@PathVariable Long id) {
        Optional<Livro> livroExistente = service.findById(id);

        if (livroExistente.isPresent()) {
            Livro livro = livroExistente.get();
            if (!livro.isEmprestado()) {
                livro.marcarComoEmprestado();
                service.save(livro);
                return new ResponseEntity<>(mapper.toDTO(livro), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<LivroDTO> devolver(@PathVariable Long id) {
        Optional<Livro> livroExistente = service.findById(id);

        if (livroExistente.isPresent()) {
            Livro livro = livroExistente.get();
            if (livro.isEmprestado()) {
                livro.marcarComoDisponivel();
                service.save(livro);
                return new ResponseEntity<>(mapper.toDTO(livro), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
