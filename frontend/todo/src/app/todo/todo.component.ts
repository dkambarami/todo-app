import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../list-todos/list-todos.component';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  username: string;
  id: number;
  todo: Todo;
  constructor(private todoDataService: TodoDataService,
    private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.username = sessionStorage.getItem('username');
    this.todo = new Todo(this.id, this.username, '',false, new Date());

    if (this.id == -1) {
      this.todo = new Todo(this.id, this.username, '',false, new Date());
    } else {
      this.todoDataService.executeTodoGetServiceWithPath(this.username, this.id).subscribe(
        data => { this.todo = data; 
        console.log(this.todo +'this is the data')}
      )
    }
  }

  saveTodo() {

    if (this.id == -1) {
      this.todoDataService.executeTodoPostServiceWithPath(this.username, this.todo)
        .subscribe(
          data => {
            console.log(data);
            this.todo=data;
            this.router.navigate(['todos']);
          }
        );

    }
    else {
      this.todoDataService.executeTodoPutServiceWithPath(this.username, this.id, this.todo)
        .subscribe(
          data => {
            console.log(data);
            this.todo=data;
            this.router.navigate(['todos']);
          }
        );
    }
  }

}
