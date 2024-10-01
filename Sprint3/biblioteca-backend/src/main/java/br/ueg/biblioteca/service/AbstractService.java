package br.ueg.biblioteca.service;

import br.ueg.biblioteca.model.GenericTabela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends GenericTabela, TYPE_ID> {
    protected abstract JpaRepository<T, TYPE_ID> getRepository();

    public T save(T entity) {
        if (entity.getIdValue() == null) {
            prepareSave(entity);
        } else {
            prepareEdit(entity);
        }

        return getRepository().save(entity);
    }


    public void delete(TYPE_ID id) {
        getRepository().deleteById(id);
    }

    public Optional<T> findById(TYPE_ID id) {
        return getRepository().findById(id);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    ;

    protected abstract void prepareEdit(T entity);

    protected abstract void prepareSave(T entity);

}
