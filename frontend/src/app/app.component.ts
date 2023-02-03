import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  constructor(private http: HttpClient) {}

  title = 'frontend';

  callGet() {
    console.log('calling /get');
    this.http
      .get('/api/get', { responseType: 'text', withCredentials: true })
      .subscribe();
  }

  callPost() {
    console.log('calling /post');
    this.http
      .post('/api/post', null, { responseType: 'text', withCredentials: true })
      .subscribe();
  }

  callServiceTwoGetDirect() {
    console.log('direct GET to service two');
    this.http
      .get('/resourcetwo/get', {
        responseType: 'text',
        withCredentials: true,
      })
      .subscribe();
  }

  callServiceTwoIndirectly() {
    console.log('indirect call to service two');
    this.http
      .get('/api/indirect', {
        responseType: 'text',
        withCredentials: true,
      })
      .subscribe();
  }

  clientget() {
    this.http.get('/api/client-get', {responseType: 'text', withCredentials: true}).subscribe();
  }
}
