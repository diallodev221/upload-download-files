import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private serverURI = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // define a function to upload files
  upload(formData: FormData): Observable<HttpEvent<String[]>> {
    return this.http.post<String[]>(`${this.serverURI}/file/upload`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  // define a function to download files
  download(filename: String): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.serverURI}/file/download/${filename}`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }

  
}
