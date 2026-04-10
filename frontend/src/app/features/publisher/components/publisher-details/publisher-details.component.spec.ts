import {ComponentFixture, TestBed} from '@angular/core/testing';
import {PublisherDetailsComponent} from './publisher-details.component';
import {provideHttpClient} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {PublisherService} from "../../service/publisher.service";
import {UserService} from "@core/auth/services/user.service";
import {BookService} from "@features/book/services/book.service";

describe('PublisherDetailsComponent', () => {
  let component: PublisherDetailsComponent;
  let fixture: ComponentFixture<PublisherDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherDetailsComponent],
      providers: [provideHttpClient(), provideRouter([]), PublisherService, BookService, UserService],
    }).compileComponents();

    fixture = TestBed.createComponent(PublisherDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
