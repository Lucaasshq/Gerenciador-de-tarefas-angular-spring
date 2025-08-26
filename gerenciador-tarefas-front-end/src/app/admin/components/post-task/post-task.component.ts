import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { User } from '../../../model/User';
import { MatButton, MatButtonModule } from "@angular/material/button";

@Component({
  selector: 'app-post-task',
  imports: [MatButtonModule],
  templateUrl: './post-task.component.html',
  styleUrl: './post-task.component.scss'
})
export class PostTaskComponent {

  constructor(private adminService:AdminService ){}

  public getUsers(){
    this.adminService.getUsers().subscribe({
      next: (res: User[]) => {
        console.log(res)
      },
      error: (err) => {
        console.error("erro ao tentar buscar usuario", err)
      }
    })
  }

}
