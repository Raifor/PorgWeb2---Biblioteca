import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevolucaoDialogComponent } from './devolucao-dialog.component';

describe('DevolucaoFormDialogComponent', () => {
  let component: DevolucaoDialogComponent;
  let fixture: ComponentFixture<DevolucaoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DevolucaoDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevolucaoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
