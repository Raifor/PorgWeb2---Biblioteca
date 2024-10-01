import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmprestimoDialogComponent } from './emprestimo-dialog.component';

describe('EmprestimoFormDialogComponent', () => {
  let component: EmprestimoDialogComponent;
  let fixture: ComponentFixture<EmprestimoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmprestimoDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmprestimoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
