export type LivroDtoType = {
  id: number,
  titulo: string,
  autor: string,
  dataEmprestimo?: Date | null,
  dataDevolucao?: Date | null,
  emprestado?: boolean | null,
}
