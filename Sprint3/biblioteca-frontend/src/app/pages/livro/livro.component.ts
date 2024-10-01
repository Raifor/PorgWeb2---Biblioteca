import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {MatToolbar} from "@angular/material/toolbar";
import {MatFabButton, MatIconButton, MatMiniFabButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow,
  MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {LivroDtoType} from "../../types/livro-dto.type";
import {DatePipe, NgIf} from "@angular/common";
import {LivroFormDialogComponent} from "./livro-form-dialog/livro-form-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {DeleteConfirmDialogComponent} from "./delete-confirm-dialog/delete-confirm-dialog.component";
import {EmprestimoDialogComponent} from "./emprestimo-dialog/emprestimo-dialog.component";
import {DevolucaoDialogComponent} from "./devolucao-dialog/devolucao-dialog.component";
import {LivroService} from "../../shared/services/livro.service";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatToolbar,
    MatFabButton,
    MatTooltip,
    MatIcon,
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRowDef,
    MatRowDef,
    MatRow,
    MatHeaderRow,
    MatIcon,
    MatIconButton,
    NgIf,
    DatePipe,
    HttpClientModule,
    MatMiniFabButton
  ],
  providers: [LivroService],
  templateUrl: './livro.component.html',
  styleUrl: './livro.component.scss'
})
export class LivroComponent implements OnInit {
  livros: LivroDtoType[] = [];
  displayedColumns: string[] = ['id', 'titulo', 'autor', 'emprestado', 'dataEmprestimo', 'dataDevolucao', 'acoes'];

  constructor(private toastr: ToastrService,
              private dialog: MatDialog,
              private service: LivroService) {
  }

  ngOnInit(): void {
    this.atualizarListaLivros();
  }

  atualizarListaLivros() {
    this.service.getAll().subscribe((data) => {
      this.livros = data;
    });
  }

  criar() {
    const dialogRef = this.dialog.open(LivroFormDialogComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.service.createOrUpdate(result).subscribe(() => {
          this.atualizarListaLivros();
          this.toastr.success('Livro criado com sucesso.');
        }, error => {
          this.toastr.error('Ocorreu um erro ao criar o livro.');
        });
      }
    });
  }

  edit(livro: LivroDtoType) {
    const dialogRef = this.dialog.open(LivroFormDialogComponent, {
      width: '400px',
      data: {...livro}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.service.createOrUpdate(result).subscribe(() => {
          this.atualizarListaLivros();
          this.toastr.success('Livro atualizado com sucesso.');
        }, error => {
          this.toastr.error('Ocorreu um erro ao atualizar o livro.');
        });
      }
    });
  }

  delete(livro: LivroDtoType) {
    const dialogRef = this.dialog.open(DeleteConfirmDialogComponent, {
      width: '400px',
      data: {titulo: livro.titulo}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.service.delete(livro.id).subscribe(() => {
          this.atualizarListaLivros();
          this.toastr.success('Livro excluído com sucesso.');
        }, error => {
          if (error.status === 400 && error.error.message) {
            this.toastr.error(error.error.message);
          } else {
            this.toastr.error('Ocorreu um erro ao excluir o livro.');
          }
        });
      }
    });
  }

  emprestar(livro: LivroDtoType) {
    const dialogRef = this.dialog.open(EmprestimoDialogComponent, {
      width: '400px',
      data: {titulo: livro.titulo}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.service.emprestar(livro.id).subscribe(() => {
          this.atualizarListaLivros();
          this.toastr.success('Livro emprestado com sucesso.');
        }, error => {
          if (error.status === 409) {
            this.toastr.error('O livro já está emprestado.');
          } else {
            this.toastr.error('Ocorreu um erro ao emprestar o livro.');
          }
        });
      }
    });
  }


  devolver(livro: LivroDtoType) {
    const dialogRef = this.dialog.open(DevolucaoDialogComponent, {
      width: '400px',
      data: {titulo: livro.titulo}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.service.devolver(livro.id).subscribe(() => {
          this.atualizarListaLivros()
          this.toastr.success('Livro devolvido com sucesso.');
        }, error => {
          if (error.status === 409) {
            this.toastr.error('O livro já está devolvido.');
          } else {
            this.toastr.error('Ocorreu um erro ao devolver o livro.');
          }
        });
      }
    });
  }

}
