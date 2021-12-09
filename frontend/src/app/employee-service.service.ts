import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from './employee.model';
import { Request } from "./request.model";
import { Observable } from 'rxjs/internal/Observable';


@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  private employeeUrl: string;

  constructor(private http: HttpClient) {
    this.employeeUrl = 'http://localhost:8080/api/employees/';
  }

  public findAll(): Observable<Employee[]>{
    return this.http.get<Employee[]>(this.employeeUrl);
  }

  public Update(requestObject : Request) {
    var url = this.employeeUrl + requestObject.id;
    return this.http.put<any>(url, requestObject);
  }
}