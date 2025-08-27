import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { User } from '../../../model/User';
import { MatButton, MatButtonModule } from "@angular/material/button";
import { Form, FormBuilder, FormGroup, Validators, ɵInternalFormsSharedModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from "../../../MaterialModule";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-task',
  imports: [MatButtonModule, ReactiveFormsModule, MaterialModule, CommonModule],
  templateUrl: './post-task.component.html',
  styleUrl: './post-task.component.scss'
})
export class PostTaskComponent {
postTask() {
throw new Error('Method not implemented.');
}

  tarefaForm!: FormGroup
  listOfFuncionarios: User[] = [];
  listOfPriority: any = ["BAIXA", "MEDIA", "ALTA"]

  constructor(private adminService: AdminService, private fb:FormBuilder) {
    this.getUsers()


    this.tarefaForm = fb.group({
      funcionarioId: [null,[Validators.required]],
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      dueDate: [null, Validators.required],
      priority: [null, Validators.required]
    })
   }



  public getUsers() {
    this.adminService.getUsers().subscribe({
      next: (res: User[]) => {
        this.listOfFuncionarios = res
        console.log(res)
      },
      error: (err) => {
        console.error("erro ao tentar buscar usuario", err)
      }
    })
  }

}
