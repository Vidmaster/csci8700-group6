import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const url = 'http://localhost:8080/api/test';

@Injectable()
export class TestService {

  constructor(private http: HttpClient) { }

  getStatus(): Observable<boolean> {
    console.log('calling service');
    return this.http.get<boolean>(url);
  }

}
