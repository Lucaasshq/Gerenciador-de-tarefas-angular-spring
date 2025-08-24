
import { Routes } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';

export const routes: Routes = [
    {path: "", redirectTo: "login", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path: "signup", component: SignupComponent},
    {path: "admin", loadComponent: ()=> 
        import("./admin/components/dashboard/dashboard.component").then(m => m.DashboardComponent)
    },
    {path: "funcionario", loadComponent: ()=> 
        import("./funcionario/components/dashboard/dashboard.component").then(m => m.DashboardComponent)
    },
];
