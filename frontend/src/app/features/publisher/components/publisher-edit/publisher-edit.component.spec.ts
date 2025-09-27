import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PublisherEditComponent} from './publisher-edit.component';
import {provideHttpClient} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {PublisherService} from "../../service/publisher.service";

describe('PublisherEditComponent', () => {
  let component: PublisherEditComponent;
  let fixture: ComponentFixture<PublisherEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherEditComponent],
      providers: [provideHttpClient(), provideRouter([]), PublisherService]
    }).compileComponents();

    fixture = TestBed.createComponent(PublisherEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
