import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {LivroDtoType} from "../../../types/livro-dto.type";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInput} from "@angular/material/input";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-livro-form-dialog',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatFormField,
    FormsModule,
    MatInput,
    MatCheckbox,
    MatDialogActions,
    MatButton,
    MatDialogClose,
    MatLabel
  ],
  templateUrl: './livro-form-dialog.component.html',
  styleUrl: './livro-form-dialog.component.scss'
})
export class LivroFormDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<LivroFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: LivroDtoType
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
