import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';

export class Todo {
  constructor(public id: number,
    public username: string,
    public description: string,
    public done: boolean,
    public targetDate: Date) { }
}


@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos = [];
  username : string;
  message = '';


  constructor(private todoDataService: TodoDataService,
    private router : Router) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username');
    console.log("The Log in user is "+this.username) ;
    this.getTodos(this.username);
  }

  getTodos(username) {
    this.todoDataService.executeTodoListServiceWithPath(username).subscribe(
      response => this.handleSuccessfulResponse(response)
    );
  }

  handleSuccessfulResponse(response) {
    this.todos = response;
    console.log(this.todos);
  }

  deleteTodo(id) {
    this.todoDataService.executeTodoDeleteServiceWithPath(this.username, id).subscribe(
      response => {
        console.log(response);
        this.message = 'successfully deleted';
      }
    );
    this.getTodos(this.username);
  }

  updateTodo(id) {
    this.router.navigate(['todos',id]);
    console.log(`update for todo ${id}`)
  }

  addTodo(){
    this.router.navigate(['todos',-1]);
  }


}
