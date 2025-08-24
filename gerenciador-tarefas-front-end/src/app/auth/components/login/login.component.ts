import { Component } from '@angular/core';
import { MaterialModule } from '../../../MaterialModule';
import { Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [MaterialModule, ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  mostrarSenha: boolean = true;;

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  alterarVisibilidadePassword(): void {
    this.mostrarSenha = !this.mostrarSenha
  }

  enviarAgora(): void {
    console.log("Login feito")
  }

}
