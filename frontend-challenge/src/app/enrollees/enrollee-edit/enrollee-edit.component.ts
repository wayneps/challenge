import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild
} from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';

import { Enrollee } from '../enrollee.model';
import { EnrolleesService } from '../enrollees.service';

@Component({
  selector: 'app-enrollee-edit',
  templateUrl: './enrollee-edit.component.html',
  styleUrls: ['./enrollee-edit.component.css']
})

export class EnrolleeEditComponent implements OnInit, OnDestroy  {
  @ViewChild('f', { static: false }) elForm: NgForm;
  subscription: Subscription;
  today : string;
  defaultState : boolean;
  states = [true, false];	
  editMode = false;
  editedItemIndex: number;
  editedItem: Enrollee;

  constructor(private eService: EnrolleesService) { 
	
}

  ngOnInit() {
	this.today = new Date().toISOString().split('T')[0];
	this.defaultState = false
    this.subscription = this.eService.startedEditing
      .subscribe(
        (index: number) => {
          this.editedItemIndex = index;
          this.editMode = true;
          this.editedItem = this.eService.getEnrollee(index);
          this.elForm.setValue({
            name: this.editedItem.name,
            birthDate: this.editedItem.birthDate,
			active: this.editedItem.active,
			id: this.editedItem.id
          })
        }
      );

  }

  onSubmit(form: NgForm) {
    const value = form.value;
    const newEnrollee = new Enrollee(value.name, value.birthDate, value.id, value.status, value.update);
    if (this.editMode) {
      this.eService.updateEnrollee(this.editedItemIndex, newEnrollee);
    } else {
      this.eService.addEnrollee(newEnrollee);
    }
    this.editMode = false;
    form.reset();
  }

  onClear() {
    this.elForm.reset();
    this.editMode = false;
  }

  onDelete() {
    this.eService.deleteEnrollee(this.editedItemIndex);
    this.onClear();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
