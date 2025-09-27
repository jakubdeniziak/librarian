import {ComponentFixture, TestBed} from '@angular/core/testing';
import {PublisherNewComponent} from './publisher-new.component';
import {provideRouter} from "@angular/router";
import {PublisherService} from "../../service/publisher.service";

describe('PublisherNewComponent', () => {
  let component: PublisherNewComponent;
  let fixture: ComponentFixture<PublisherNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherNewComponent],
      providers: [provideRouter([]), PublisherService],
    }).compileComponents();

    fixture = TestBed.createComponent(PublisherNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
