import { Component, OnInit } from '@angular/core';
import { Employee } from './employee.model';
import { EMPLOYEES } from './mock-employees';
import { EmployeeServiceService } from './employee-service.service';
import { Request } from './request.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  employees = EMPLOYEES;
  request: Request = {
    id: 0,
    workDays: 0,
    vacationTime: 0.0
  };

  selectedEmployee : Employee = {id : 0, name: '', employeeType: '', workDaysPerYear: 0, vacationDaysAvailable: 0.0 };
  displayMessages: string = ""; //todo: retrieve message from http response header for ui display 

  constructor(private employeeService: EmployeeServiceService) {
  }

  ngOnInit() {
    this.refreshData();
  }

  onSelect(employee: number): void { 
    this.refreshData();
    this.employees.forEach(emp => { //This for loop is inefficient but it works, currently returns the employee id
      //there may be performace issues for large datasets therefore a better solution is to return the exact object onSelect() or
      //an index that allows us to use optimized searching algotithms present in native js functions when searching indexes. 
      //I am new to angular so it took neary a day just on this function so I was in a rush
      if(emp.id == employee) {
        this.selectedEmployee = emp;
        this.request.id = emp.id;
      }
    })
  }

  async onButtonClicked() {
    await this.employeeService.Update(this.request).subscribe({
        error: error => {
          this.displayMessages = error.error.text != undefined ? this.displayMessages = error.error.text : error.error;
        }
      }
    );
    this.clearInput();
    await this.refreshData();
  }

  onWorkDayEntry(work: number){
    this.request.workDays = work;
    this.clearErrorMessage();
  }

  onVacationDayEntry(vacation: number){
    this.request.vacationTime = vacation;
    this.clearErrorMessage();
  }

  refreshData(){
    this.employeeService.findAll().subscribe(springbootData => {
      this.employees = springbootData;
    })
  }

  clearInput(){
    this.request.workDays = 0;
    this.request.vacationTime = 0;
  }

  onRefreshButtonClicked(){
    this.onSelect(this.request.id)
    this.clearErrorMessage();
  }

  clearErrorMessage(){
    this.displayMessages = "";
  }
}
