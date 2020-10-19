import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EnrolleesComponent } from './enrollees/enrollees.component';
import { AuthComponent } from './auth/auth.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/enrollees', pathMatch: 'full' },
  { path: 'enrollees', component: EnrolleesComponent },
  { path: 'auth', component: AuthComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
