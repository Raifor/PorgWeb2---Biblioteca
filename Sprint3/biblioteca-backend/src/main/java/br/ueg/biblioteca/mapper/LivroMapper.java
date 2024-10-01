package br.ueg.biblioteca.mapper;

import br.ueg.biblioteca.model.Livro;
import br.ueg.biblioteca.model.dto.LivroDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    LivroDTO toDTO(Livro livro);

    Livro toEntity(LivroDTO livroDto);

    List<LivroDTO> toDTOList(List<Livro> livroList);
}
