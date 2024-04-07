import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublisherNewComponent } from './publisher-new.component';

describe('PublisherNewComponent', () => {
  let component: PublisherNewComponent;
  let fixture: ComponentFixture<PublisherNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublisherNewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublisherNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
