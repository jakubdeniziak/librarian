import {ComponentFixture, TestBed} from '@angular/core/testing';
import {provideHttpClient} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {AuthorDetailsComponent} from './author-details.component';
import {UserService} from "../../../../user/service/user.service";
import {AuthorService} from "../../services/author.service";
import {BookService} from "../../../../book/services/book.services";

describe('AuthorDetailsComponent', () => {
  let component: AuthorDetailsComponent;
  let fixture: ComponentFixture<AuthorDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorDetailsComponent],
      providers: [provideHttpClient(), provideRouter([]), UserService, AuthorService, BookService],
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
