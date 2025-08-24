import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../../../model/LoginRequest';
import { LoginResponse } from '../../../model/LoginResponse';

 const BASE_URL = "http://localhost:8080/auth";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 

  constructor( private http: HttpClient) { }

  public signup(signup: any): Observable<any>{
   return this.http.post<any>(`${BASE_URL}/signup`, signup)
  }

 public login(loginRequest: LoginRequest): Observable<LoginResponse>{
  return this.http.post<LoginResponse>(`${BASE_URL}/login`,loginRequest )
 }
}
