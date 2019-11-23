import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';

@Component({
  selector: 'return-item',
  templateUrl: './return-item.component.html',
  providers: [LibraryService]
})
export class ReturnItemComponent implements OnInit {

  freeSpace: any[] = [];
  returnErrMessage: string = null;
  returnSucMessage: string = null;
  isbnStr: string;
  lateFee: number;

  constructor(private _libraryService: LibraryService) { }

  ngOnInit() {
  }

  onSubmit() {

    this._libraryService.returnIsbn(this.isbnStr)
      .subscribe(res => {
        console.log(res.json())
        let response = res.json();
        if (response.status) {

          let hrs = response.hours;
          let lateHrs = response.itemType == 1 ? hrs - 168 : hrs - 72; //remove 7 days if a book, 3 days if a dvd

          if (lateHrs > 0) {
            if (lateHrs <= 72) {
              this.lateFee = lateHrs * 0.2;
            } else {
              this.lateFee = 72 * 0.2 + (lateHrs - 72) * 0.5;
            }
            this.returnErrMessage = "Your overdue fee is " + this.lateFee + ". Please pay the amount to return the item.";
          }
          
        } else {
          // this.returnErrMessage = "Return will be completed after the payment of late fee of Rs."
          //   + response.status + "/=";
          this.returnSucMessage = "Thank you for returning the item on time.";
        }
        //display free spaces
        this.freeSpace = response.spaceLeft;

      }),
      error => {
        console.log(error)
      };



  }

}
