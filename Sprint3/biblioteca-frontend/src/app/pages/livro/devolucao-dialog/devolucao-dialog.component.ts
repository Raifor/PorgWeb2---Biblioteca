import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-devolucao-dialog',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatButton,
    MatDialogClose
  ],
  templateUrl: './devolucao-dialog.component.html',
  styleUrl: './devolucao-dialog.component.scss'
})
export class DevolucaoDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DevolucaoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { titulo: string }
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
