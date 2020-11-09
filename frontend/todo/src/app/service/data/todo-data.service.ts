import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { Todo } from 'src/app/list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {


  constructor(private http: HttpClient) { }

  executeTodoListServiceWithPath(username) {
    return this.http.get<Todo[]>(`${API_URL}users/${username}/todos`);
  }

  executeTodoDeleteServiceWithPath(username,id) {
    return this.http.delete(`${API_URL}users/${username}/todos/${id}`);
  }

  executeTodoGetServiceWithPath(username,id) {
    return this.http.get<Todo>(`${API_URL}users/${username}/todos/${id}`);
  }

  executeTodoPutServiceWithPath(username,id, todo : Todo) {
    return this.http.put<Todo>(`${API_URL}users/${username}/todos/${id}`, todo);
  }

  executeTodoPostServiceWithPath(username,todo : Todo) {
    return this.http.post<Todo>(`${API_URL}users/${username}/todos/`, todo);
  }

}
