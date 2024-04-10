import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibraryAddBookComponent } from './library-add-book.component';

describe('LibraryAddBookComponent', () => {
  let component: LibraryAddBookComponent;
  let fixture: ComponentFixture<LibraryAddBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LibraryAddBookComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LibraryAddBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
