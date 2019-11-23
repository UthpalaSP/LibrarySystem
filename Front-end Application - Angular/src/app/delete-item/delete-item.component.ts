import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';

@Component({
  selector: 'delete-item',
  templateUrl: './delete-item.component.html',
  providers: [LibraryService]
  // styleUrls: ['./library-item.component.css']
})
export class DeleteItemComponent implements OnInit {

  freeSpace: any[] = [];
  deleteMessage: string = null;
  isbnStr: string;
  isDeleted: boolean = false;

  constructor(private _libraryService: LibraryService) { }

  ngOnInit() {
  }

  onSubmit() {
    //delete succes and returns the type
    this._libraryService.deleteIsbn(this.isbnStr)
      .subscribe(res => {
        console.log(res.json())

        let type = res.json().itemType;
        if (type == 1) {
          this.deleteMessage = "The book has been deleted";
        } else {
          this.deleteMessage = "The DVD has been deleted";
        }
        this.isDeleted = true;
        //display free spaces
        this.freeSpace = res.json().spaceLeft;
      }),
      error => {
        console.log(error)
      }



  }

}
