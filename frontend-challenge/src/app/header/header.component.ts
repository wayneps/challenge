import { Component } from '@angular/core';

import { DataService } from '../model/data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(private dataService: DataService) {}

  onSaveData() {
    this.dataService.storeEnrollees();
  }

  onLoadData() {
    this.dataService.loadEnrollees();
  }
}
