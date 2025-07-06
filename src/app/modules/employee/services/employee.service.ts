import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }


  getEmployeeTasksById() : Observable<any>{
    return this.http.get(BASIC_URL+'api/employee/tasks',{
      headers:this.createAuthorizationHeader()
    })
  }


    private createAuthorizationHeader(): HttpHeaders {
      return new HttpHeaders().set(
        'Authorization',
        'Bearer ' + StorageService.getToken()
      );
    }
}
