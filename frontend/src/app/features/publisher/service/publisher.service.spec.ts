import {TestBed} from '@angular/core/testing';
import {PublisherService} from "./publisher.service";
import {provideHttpClient} from "@angular/common/http";

describe('PublisherService', () => {
  let service: PublisherService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(PublisherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
