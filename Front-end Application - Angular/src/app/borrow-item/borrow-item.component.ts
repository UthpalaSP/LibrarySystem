import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';

@Component({
  selector: 'borrow-item',
  templateUrl: './borrow-item.component.html',
  providers: [LibraryService]
})
export class BorrowItemComponent implements OnInit {

  freeSpace: any[] = [];
  borrowErrMessage: string = null;
  borrowSucMessage: string = null;
  isbn: string;
  model: LibraryItem = new LibraryItem;

  constructor(private _libraryService: LibraryService) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.model)
    this._libraryService.borrowIsbn(this.model)
      .subscribe(res => {
        console.log(res.json())

        let response = res.json();
        if (response.status) {
            this.borrowSucMessage = "The item has been borrowed";
        } else {
          this.borrowErrMessage = "You cannot borrow the item until " + response.status.year + 
          "-" + response.status.month + "-" + response.status.day;
        }
        //display free spaces
        this.freeSpace = response.spaceLeft;
      }),
      error => {
        console.log(error)
      }
  }

}
