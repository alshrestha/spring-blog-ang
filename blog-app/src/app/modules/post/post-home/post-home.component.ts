import { Comment } from './../../../../core/model/Comment';
import { Component, OnInit, Input } from '@angular/core';
import { HttpService } from '../../../../core/services/http.service';
import { Post } from '../../../../core/model/Post';

@Component({
  selector: 'app-post-home',
  templateUrl: './post-home.component.html',
  styleUrls: ['./post-home.component.scss']
})
export class PostHomeComponent implements OnInit {

  @Input() header: any;
  blogData: Post[];

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
  }

  fetchPost() {
    this.httpService.getFetchPost().subscribe((data => 
      { 
        console.log(data);
        this.blogData = data;
      }
      ));
  }

}
