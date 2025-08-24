import { Injectable } from '@angular/core';

import { User } from '../../../model/User';

const TOKEN = "token";
const USER = "user";


@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }


  static salvarToken(token: string):void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static salvarUser(user:any){
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }


  static getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): User | null {
  const dado = localStorage.getItem(USER)
    if(dado == null){
      return null
    }

    return JSON.parse(dado);
  }

  static getRole(): string{
    const user = this.getUser();
    if (user == null){
      return "";
    }
   return user.role;
  }


  static eAdminLogado(): boolean {
    if (this.getUser == null){
      return false;
    }
    const role:string = this.getRole();
    return role == "ADMIN";
  }

   static eFuncionarioLogado(): boolean {
    if (this.getUser == null){
      return false;
    }
    const role:string = this.getRole();
    return role == "FUNCIONARIO";
  }


  static getUserId(): string {
    const user = this.getUser();
    if (user == null){
      return "";
    }
   return user.id;
  }


  static logout():void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }



}
