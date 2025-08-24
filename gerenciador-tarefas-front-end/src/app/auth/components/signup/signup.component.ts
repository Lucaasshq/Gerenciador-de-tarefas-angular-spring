
import { Component } from '@angular/core';
import { MaterialModule } from "../../../MaterialModule";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  imports: [MaterialModule, CommonModule, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signupForm!: FormGroup;
  mostrarSenha = true;

  constructor(private fb: FormBuilder) {
    this.signupForm = this.fb.group({
      nome: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      confirmarPassword: [null, [Validators.required]]
    })
  }

  alternarVisibilidadePassword() {
    this.mostrarSenha = !this.mostrarSenha
  }

  enviarAgora() {
    console.log("enviado")
  }

}
