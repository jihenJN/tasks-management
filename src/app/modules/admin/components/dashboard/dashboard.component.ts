import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  listOfTasks:any=[];

  constructor(private service:AdminService){
    this.gatTasks();
  }

  gatTasks(){
    this.service.getAllTasks().subscribe((res)=>{
      console.log(res);
      this.listOfTasks=res;
    })
  }
}
