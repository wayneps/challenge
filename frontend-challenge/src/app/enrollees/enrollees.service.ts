import { Enrollee } from './enrollee.model';
import { Subject } from 'rxjs';

export class EnrolleesService {
  enrolleesChanged = new Subject<Enrollee[]>();
  startedEditing = new Subject<number>();
  private enrollees: Enrollee[] = [new Enrollee('No enrollees.', '', '', false, false)];
  private removedIds: string[] = [];

  getEnrollees() {
    return this.enrollees.slice();
  }

  getEnrollee(index: number) {
    return this.enrollees[index];
  }

  getRemovedIds() {
    return this.removedIds;
  }

  addEnrollee(enrollee: Enrollee) {	
    this.enrollees.push(enrollee);
    this.enrolleesChanged.next(this.enrollees.slice());
  }

  addEnrollees(enrollees: Enrollee[]) {
	console.log('addEnrollees');
    for (let enrollee of enrollees) {
	enrollee.update = false;
      this.addEnrollee(enrollee);
    }
    this.enrolleesChanged.next(this.enrollees.slice());
  }

  updateEnrollee(index: number, newEnrollee: Enrollee) {
    this.enrollees[index] = newEnrollee;
	newEnrollee.update = true;
    this.enrolleesChanged.next(this.enrollees.slice());
  }

  deleteEnrollee(index: number) {
	
	this.removedIds.push(this.enrollees[index].id);	
    this.enrollees.splice(index, 1);
    this.enrolleesChanged.next(this.enrollees.slice());
  }

  clearEnrollees() {
	this.enrollees = [];
    this.enrolleesChanged.next(this.enrollees.slice());
  }
}
