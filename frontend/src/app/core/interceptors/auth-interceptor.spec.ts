import {HttpEvent, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {of} from 'rxjs';
import {TestBed} from '@angular/core/testing';
import {JwtService} from "../auth/services/jwt.service";
import {authInterceptor} from "./auth-interceptor";

describe('authInterceptor', () => {
  const TOKEN: string = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30';

  let jwtService: jasmine.SpyObj<JwtService>;

  beforeEach(() => {
    const jwtSpy = jasmine.createSpyObj('JwtService', ['isTokenPresent', 'getToken']);

    TestBed.configureTestingModule({
      providers: [
        {provide: JwtService, useValue: jwtSpy}
      ]
    });

    jwtService = TestBed.inject(JwtService) as jasmine.SpyObj<JwtService>;
  });

  it('should forward request unchanged if no token is present', (done) => {
    jwtService.isTokenPresent.and.returnValue(false);
    const req = new HttpRequest('GET', '/api/test');
    const next: HttpHandlerFn = (nextReq) => {
      expect(nextReq).toBe(req);
      return of({} as HttpEvent<unknown>);
    };
    TestBed.runInInjectionContext(() => {
      authInterceptor(req, next).subscribe(() => done());
    });
  });

  it('should add Authorization header if token is present', (done) => {
    jwtService.isTokenPresent.and.returnValue(true);
    jwtService.getToken.and.returnValue(TOKEN);
    const req = new HttpRequest('GET', '/api/test');
    const next: HttpHandlerFn = (nextReq) => {
      expect(nextReq.headers.get('Authorization')).toBe(`Bearer ${TOKEN}`);
      return of({} as HttpEvent<unknown>);
    };
    TestBed.runInInjectionContext(() => {
      authInterceptor(req, next).subscribe(() => done());
    });
  });
});
