import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { Enrollee } from '../enrollees/enrollee.model';
import { EnrolleesService } from './enrollees.service';
import { DataService } from '../model/data.service';

@Component({
  selector: 'app-enrollees',
  templateUrl: './enrollees.component.html',
  styleUrls: ['./enrollees.component.css'],

})
export class EnrolleesComponent implements OnInit, OnDestroy {
  enrollees: Enrollee[];
  currentTutorial = null;
  currentIndex = -1;
  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];
  private subscription: Subscription;

  constructor(private dataService: DataService, private elService: EnrolleesService) { }

  ngOnInit() {
    this.enrollees = this.elService.getEnrollees();
    this.subscription = this.elService.enrolleesChanged
      .subscribe(
        (enrollees: Enrollee[]) => {
          this.enrollees = enrollees;
        }
      );
  }

  filteredActive = '';
  getStateClasses(enrollee: {instanceType: string, name: string, date: string, id: string, status: string, active:boolean}) {
    return {
      'table-success': enrollee.active === true,
      'table-active': enrollee.active === false
       //'list-group-item-danger': enrollee.active === 'critical'
    };
  }
  onEditItem(index: number) {
    this.elService.startedEditing.next(index);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getBackgroundColor(enrollee: Enrollee){
	return enrollee.active === true ? 'lightBlue' : 'lightGrey';
  }

  getColor(enrollee: Enrollee){
	return enrollee.active === true ? 'black' : 'grey';
  }


  setActiveEnrollee(enrollee: Enrollee, index: number) : void{
	
  }

  handlePageChange(event): void {
    this.page = event;
	this.dataService.loadEnrollees();
  }

  handlePageSizeChange(event): void {
    this.pageSize = event.target.value;
    this.page = 1;
	this.dataService.loadEnrollees();

  }
}
