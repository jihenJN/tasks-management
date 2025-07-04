import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css'],
})
export class UpdateTaskComponent {
  id: number = this.route.snapshot.params['id'];
 updatetaskForm!: FormGroup;
  listOfEmployees: any = [];
  listOfPriorities: any = ['LOW', 'MEDIUM', 'HIGH'];
  listOfTaskStatus: any = [ 'PENDING', 'INPROGRESS', 'COMPLETED', 'DEFERRED', 'CANCELED'];
  constructor(private adminService: AdminService,  private snackBar:MatSnackBar,
      private router:Router,private service: AdminService,private fb: FormBuilder, private route: ActivatedRoute) {
    this.getUsers();
    this.getTaskById();
     this.updatetaskForm = this.fb.group({
          employeeId: [null, [Validators.required]],
          title: [null, [Validators.required]],
          description: [null, [Validators.required]],
          dueDate: [null, [Validators.required]],
          priority: [null, [Validators.required]],
          taskStatus: [null, [Validators.required]],
        });
     console.log(this.id);
  }

  getTaskById() {
    this.service.getTaskById(this.id).subscribe((res) => {
     this.updatetaskForm.patchValue(res);
      console.log(res);
    });
  }

    getUsers() {
    this.adminService.getUsers().subscribe((res) => {
      this.listOfEmployees = res;

      console.log(res);
    });
  }

   updateTask() {
    console.log(this.updatetaskForm.value);
    this.adminService.updateTask(this.id,this.updatetaskForm.value).subscribe((res)=>{
      if(res.id!=null){
       this.snackBar.open("Task updated successfully","Close",{duration:5000})
       this.router.navigateByUrl("/admin/dashboard")
      } else{
         this.snackBar.open("Something went wrong","ERROR",{duration:5000})
      }
    });
  }
}
