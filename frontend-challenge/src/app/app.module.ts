import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { EnrolleesComponent } from './enrollees/enrollees.component';
import { EnrolleeEditComponent } from './enrollees/enrollee-edit/enrollee-edit.component';
import { DropdownDirective } from './model/dropdown.directive';
import { EnrolleesService } from './enrollees/enrollees.service';
import { AppRoutingModule } from './app-routing.module';
import { AuthComponent } from './auth/auth.component';
import { StatusPipe } from './enrollees/enrollee-edit/status.pipe';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EnrolleesComponent,
    EnrolleeEditComponent,
    DropdownDirective,
    AuthComponent,
	StatusPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
	NgxPaginationModule,
    AppRoutingModule
  ],
  providers: [EnrolleesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
