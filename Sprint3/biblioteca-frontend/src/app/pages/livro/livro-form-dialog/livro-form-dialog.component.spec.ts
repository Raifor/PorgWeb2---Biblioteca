import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LivroFormDialogComponent } from './livro-form-dialog.component';

describe('LivroFormDialogComponent', () => {
  let component: LivroFormDialogComponent;
  let fixture: ComponentFixture<LivroFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LivroFormDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LivroFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
