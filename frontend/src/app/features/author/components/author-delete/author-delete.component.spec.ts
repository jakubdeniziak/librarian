import {ComponentFixture, TestBed} from '@angular/core/testing';
import {provideHttpClient} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {AuthorDeleteComponent} from './author-delete.component';
import {AuthorService} from "../../services/author.service";

describe('AuthorDeleteComponent', () => {
  let component: AuthorDeleteComponent;
  let fixture: ComponentFixture<AuthorDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorDeleteComponent],
      providers: [provideHttpClient(), provideRouter([]), AuthorService]
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
