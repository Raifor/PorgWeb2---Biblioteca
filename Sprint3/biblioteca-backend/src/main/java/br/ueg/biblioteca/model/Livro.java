package br.ueg.biblioteca.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "LIVRO")
public class Livro extends GenericTabela{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 200, name = "TITULO")
    private String titulo;

    @Column(nullable = false, length = 200, name = "AUTOR")
    private String autor;

    @Column(name = "DATA_EMPRESTIMO", nullable = true)
    private Date dataEmprestimo;

    @Column(name = "DATA_DEVOLUCAO", nullable = true)
    private Date dataDevolucao;

    @Column(name = "IS_EMPRESTADO", nullable = true)
    private boolean emprestado = false;

    public void marcarComoEmprestado() {
        this.setEmprestado(true);
        this.setDataEmprestimo(new Date());
    }

    public void marcarComoDisponivel() {
        this.setEmprestado(false);
        this.setDataDevolucao(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}
