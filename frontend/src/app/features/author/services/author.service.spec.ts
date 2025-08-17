import {TestBed} from '@angular/core/testing';
import {AuthorService} from "./author.service";
import {provideHttpClient} from "@angular/common/http";

describe('AuthorService', () => {
  let service: AuthorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient()]
    });
    service = TestBed.inject(AuthorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
