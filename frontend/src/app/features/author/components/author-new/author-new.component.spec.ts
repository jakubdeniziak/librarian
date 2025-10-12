import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AuthorNewComponent} from './author-new.component';
import {provideRouter} from "@angular/router";
import {AuthorService} from "../../services/author.service";
import {provideHttpClient} from "@angular/common/http";

describe('AuthorNewComponent', () => {
  let component: AuthorNewComponent;
  let fixture: ComponentFixture<AuthorNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthorNewComponent],
      providers: [provideHttpClient(), provideRouter([]), AuthorService]
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
