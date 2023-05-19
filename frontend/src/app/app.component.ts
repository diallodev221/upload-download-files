import { Component } from '@angular/core';
import { FileService } from './services/file.service';
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { FileStatus } from './model/filestatus';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  filenames: String[] = [];
  fileStatus!: FileStatus;

  constructor(private fileService: FileService) { }
  
  onUpload(files:Event) {
    
    console.log(files);
    
  }
  
  // upload files
  onUploadFiles(files: File[]): void {
    const formData = new FormData();
    for (const file of files) {
      formData.append('files', file, file.name);
    }
    this.fileService.upload(formData).subscribe(event => {
      console.log(event);
      this.reportProgress(event);
    },
    (error: HttpErrorResponse) => console.log(error))
  }
  
  // download files
  onDownloadFiles(filename: String): void {
    this.fileService.download(filename).subscribe(event => {
      console.log(event);
      this.reportProgress(event);
    },
    (error: HttpErrorResponse) => console.log(error))
  }

  private reportProgress(event: HttpEvent<String[] | Blob>): void {
    switch (event.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(event.loaded, event.total!, 'Uploading');
        break
        case HttpEventType.DownloadProgress:
          this.updateStatus(event.loaded, event.total!, 'Downloading');
        break
      case HttpEventType.Response:
        if (event.body instanceof Array) {
          this.fileStatus.status = 'done';
          for (const filename of event.body) {
            this.filenames.unshift(filename)
          }
        } else {
          // download logic
          // add filesaver library
          
          // saveAs(new File(event.body), event.headers.get('file-name'), type: `${event.headers.get('content-Type')};charSet=utf-8`)

        }
        this.fileStatus.status = 'done';
        break;
      default:
        console.log(event);
        
        
    }
  }

  private updateStatus(loaded: number, total: number, requestType: string) {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }
  
}
