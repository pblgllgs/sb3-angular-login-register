import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root',
})
export class TokenService {

  set token(token: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('username',this.getSubjectFromToken(token))
  }

  get token(){
    return localStorage.getItem('token') as string;
  }

  isTokenNotValid() {
    return !this.isTokenValid();
  }

  private isTokenValid() {
    const token = this.token;
    if(!token){
      return false;
    }
    const jwtHelper = new JwtHelperService();
    const isTokenExpired = jwtHelper.isTokenExpired(token);
    if(isTokenExpired) {
      localStorage.clear();
    }
    return true;
  }

  public getSubjectFromToken(token: string): string {
    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);
    return decodedToken ? decodedToken.sub : null;
  }
}
