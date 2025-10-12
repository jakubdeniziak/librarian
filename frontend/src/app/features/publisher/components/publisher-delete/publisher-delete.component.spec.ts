import {ComponentFixture, TestBed} from '@angular/core/testing';
import {provideHttpClient} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {PublisherDeleteComponent} from './publisher-delete.component';
import {PublisherService} from "../../service/publisher.service";

describe('PublisherDeleteComponent', () => {
  let component: PublisherDeleteComponent;
  let fixture: ComponentFixture<PublisherDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherDeleteComponent],
      providers: [provideHttpClient(), provideRouter([]), PublisherService]
    }).compileComponents();

    fixture = TestBed.createComponent(PublisherDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
