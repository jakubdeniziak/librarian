import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PublisherListComponent} from './publisher-list.component';
import {UserService} from "@core/auth/services/user.service";
import {PublisherService} from "../../service/publisher.service";

describe('PublisherListComponent', () => {
  let component: PublisherListComponent;
  let fixture: ComponentFixture<PublisherListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherListComponent],
      providers: [UserService, PublisherService],
    }).compileComponents();

    fixture = TestBed.createComponent(PublisherListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
