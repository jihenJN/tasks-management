import { EmployeeService } from './../../services/employee.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  listOfTasks: any = [];

  constructor(private service: EmployeeService) {
    this.getTasks();
  }

  getTasks() {
    this.service.getEmployeeTasksById().subscribe((res) => {
      console.log(res);
      this.listOfTasks = res;
    });
  }
}
