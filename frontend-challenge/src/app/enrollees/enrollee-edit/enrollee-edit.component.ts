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
import { DataService } from '../../model/data.service';

@Component({
  selector: 'app-enrollee-edit',
  templateUrl: './enrollee-edit.component.html',
  styleUrls: ['./enrollee-edit.component.css']
})

export class EnrolleeEditComponent implements OnInit, OnDestroy  {
  @ViewChild('f', { static: false }) elForm: NgForm;
  subscription: Subscription;
  today : string;
  name : string;
  defaultState : boolean;
  states = [true, false];	
  editMode = false;
  editedItemIndex: number;
  editedItem: Enrollee;

  constructor(private dataService: DataService, private eService: EnrolleesService) { 
	
}

  ngOnInit() {
	this.today = new Date().toISOString().split('T')[0];
	this.name = '';
	this.defaultState = false
    this.subscription = this.eService.startedEditing
      .subscribe(
        (index: number) => {
          this.editedItemIndex = index;
          this.editMode = true;
          this.editedItem = this.eService.getEnrollee(index);
		  this.name = this.eService.getEnrollee(index).name;
          this.elForm.setValue({
            name: this.editedItem.name,
            dateOfBirth: this.editedItem.dateOfBirth,
			active: this.editedItem.active,
			id: this.editedItem.id
          })
        }
      );
	this.dataService.loadEnrollees();
  }

  onSubmit(form: NgForm) {
    const value = form.value;
    const newEnrollee = new Enrollee(value.name, value.dateOfBirth, value.id, value.update, value.active);
    if (this.editMode) {
      this.eService.updateEnrollee(this.editedItemIndex, newEnrollee);
    } else {
      this.eService.addEnrollee(newEnrollee);
    }
	this.dataService.storeEnrollees();
    this.editMode = false;
    //form.reset();
  }

  onClear() {
    this.elForm.reset();
    this.editMode = false;
	this.name = '';
  }

  onDelete() {
    this.eService.deleteEnrollee(this.editedItemIndex);
    this.onClear();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSearch() {
	this.dataService.searchEnrollees(this.name);
    this.onClear();
  }
}
