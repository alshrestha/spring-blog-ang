import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Post } from '../model/Post';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  readonly fetchPost: string = 'http://localhost:8080/posts/all';
  constructor(private httpClient: HttpClient) {

   }

   getFetchPost(): Observable<Post[]>{

    return this.httpClient.get<Post[]>(this.fetchPost).pipe(map(response => {return response;}));


   }
}
