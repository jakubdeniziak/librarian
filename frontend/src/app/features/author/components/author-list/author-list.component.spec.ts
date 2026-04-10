import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AuthorListComponent} from './author-list.component';
import {provideHttpClient} from "@angular/common/http";
import {UserService} from "@core/auth/services/user.service";
import {AuthorService} from "../../services/author.service";

describe('AuthorListComponent', () => {
  let component: AuthorListComponent;
  let fixture: ComponentFixture<AuthorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorListComponent],
      providers: [provideHttpClient(), UserService, AuthorService]
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
