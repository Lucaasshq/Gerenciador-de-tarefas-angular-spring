import { Routes } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';

export const routes: Routes = [
    {path: "", redirectTo: "login", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path: "signup", component: SignupComponent},
    {path: "admin", loadComponent: ()=> 
        import("./admin/components/admin/admin.component").then(m => m.AdminComponent)
    },
    {path: "funcionario", loadComponent: ()=> 
        import("./funcionario/components/funcionario/funcionario.component").then(m => m.FuncionarioComponent)
    },
];
