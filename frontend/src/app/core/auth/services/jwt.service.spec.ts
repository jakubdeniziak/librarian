import {TestBed} from '@angular/core/testing';

import {JwtService} from './jwt.service';

describe('JwtService', () => {
  const TOKEN_KEY: string = "auth_token";
  const TOKEN: string = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30"

  let service: JwtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JwtService);
    sessionStorage.clear();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getToken', () => {
    it('should return the token if it exists in session storage', () => {
      sessionStorage.setItem(TOKEN_KEY, TOKEN);
      expect(service.getToken()).toBe(TOKEN);
    });

    it('should throw an error if token is missing', () => {
      sessionStorage.removeItem(TOKEN_KEY);
      expect(() => service.getToken()).toThrowError('Auth token is missing from session storage.');
    });
  });

  describe('saveToken', () => {
    it('should save the token in session storage', () => {
      service.saveToken(TOKEN);
      expect(sessionStorage.getItem(TOKEN_KEY)).toBe(TOKEN);
    });
  });

  describe('destroyToken', () => {
    it('should remove the token from session storage', () => {
      sessionStorage.setItem(TOKEN_KEY, TOKEN);
      service.destroyToken();
      expect(sessionStorage.getItem(TOKEN_KEY)).toBeNull();
    });
  });

  describe('isTokenPresent', () => {
    it('should return true if token exists', () => {
      sessionStorage.setItem(TOKEN_KEY, 'token-value');
      expect(service.isTokenPresent()).toBeTrue();
    });

    it('should return false if token does not exist', () => {
      sessionStorage.removeItem(TOKEN_KEY);
      expect(service.isTokenPresent()).toBeFalse();
    });

    it('should return false if token is an empty string', () => {
      sessionStorage.setItem(TOKEN_KEY, '');
      expect(service.isTokenPresent()).toBeFalse();
    });
  });
});
