import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';

@Component({
  selector: 'report',
  templateUrl: './report.component.html',
  providers: [LibraryService]
})
export class ReportComponent implements OnInit {

  items: any[] = [];
  freeSpace: any[] = [];

  constructor(private _libraryService: LibraryService) { }

  ngOnInit() {

    this._libraryService.report()
      .subscribe(res => {
        console.log(res.json())
        this.items = res.json().dueList;
        this.items.forEach(function(item, index){
          let lateFee =0 ;
          if (item.lateHrs <= 72) {
            lateFee = item.lateHrs * 0.2;
          } else {
            lateFee = 72 * 0.2 + (item.lateHrs - 72) * 0.5;
          }
          item.lateFee = lateFee;
        })

      }),
      error => {
        console.log(error)
      };

  }

  onSubmit(){
    console.log(this.items)
  }

  edit(id: string){

  }

  delete(id: string){
    
  }

}
