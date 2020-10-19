import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {
  transform(value: boolean) {
    if (value === true) {
      return 'Active';
    }
    return 'Inactive';
  }
}