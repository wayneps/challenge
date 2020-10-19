import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { Enrollee } from '../enrollees/enrollee.model';
import { EnrolleesService } from './enrollees.service';

@Component({
  selector: 'app-enrollees',
  templateUrl: './enrollees.component.html',
  styleUrls: ['./enrollees.component.css']
})
export class EnrolleesComponent implements OnInit, OnDestroy {
  enrollees: Enrollee[];
  private subscription: Subscription;

  constructor(private elService: EnrolleesService) { }

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
      'list-group-item-success': enrollee.active === true,
      'list-group-item-warning': enrollee.active === false
       //'list-group-item-danger': enrollee.active === 'critical'
    };
  }
  onEditItem(index: number) {
    this.elService.startedEditing.next(index);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
