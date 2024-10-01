package br.ueg.biblioteca.model;

public class GenericTabela {
    public Object getIdValue(){
        return ReflexaoTabela.getIdValue(this);
    };
}
