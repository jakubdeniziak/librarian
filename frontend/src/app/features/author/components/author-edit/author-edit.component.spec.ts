import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AuthorEditComponent} from './author-edit.component';
import {provideRouter} from "@angular/router";
import {AuthorService} from "../../services/author.service";
import {provideHttpClient} from "@angular/common/http";

describe('AuthorEditComponent', () => {
  let component: AuthorEditComponent;
  let fixture: ComponentFixture<AuthorEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorEditComponent],
      providers: [provideHttpClient(), provideRouter([]), AuthorService]
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
