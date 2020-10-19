import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

import { Enrollee } from '../enrollees/enrollee.model';
import { EnrolleesService } from '../enrollees/enrollees.service';

@Injectable({ providedIn: 'root' })
export class DataService {
  constructor(private http: HttpClient, private enrolleesService: EnrolleesService) {}

  storeEnrollees() {
	let username='admin1'
    let password='password1'
	const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    const enrollees = this.enrolleesService.getEnrollees();
	const removedIds = this.enrolleesService.getRemovedIds();
	console.log(enrollees);
	for (let enrollee of enrollees) {
      if(enrollee.update){
	
		  this.http.post(environment.apiUrl + '/enrollees',enrollee, {headers})
	      .subscribe(response => {
	        console.log(response);
	      });
	  }
    }
	for (let removedId of removedIds) {
		
	  this.http.delete(environment.apiUrl + '/enrollees/' + removedId, {headers})
      .subscribe(response => {
        console.log(response);
      });
    }
  }

  loadEnrollees() {
	let username='admin1'
    let password='password1'
	const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });	
	console.log('loadEnrollees');
    return this.http.get<Enrollee[]>( environment.apiUrl + '/enrollees', {headers})
      .subscribe(enrollees => {
		this.enrolleesService.clearEnrollees();
		this.enrolleesService.addEnrollees(enrollees);
        console.log(enrollees);
      });
  }
}
