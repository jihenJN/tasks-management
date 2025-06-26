import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-post-task',
  templateUrl: './post-task.component.html',
  styleUrls: ['./post-task.component.css']
})
export class PostTaskComponent {
  constructor(private adminService:AdminService){
    this.getUsers();
  }
  getUsers(){
    this.adminService.getUsers().subscribe((res)=>{
      console.log(res);
    })
  }
}
