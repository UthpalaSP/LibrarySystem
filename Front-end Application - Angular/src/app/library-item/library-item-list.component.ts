import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';
import { Router } from '@angular/router';

@Component({
  selector: 'library-item-list',
  templateUrl: './library-item-list.component.html',
  providers: [LibraryService]
  // styleUrls: ['./library-item.component.css']
})
export class LibraryItemListComponent implements OnInit {

  items: LibraryItem[] = [];
  freeSpace: any[] = [];
  deleteMessage: string = null;
  itemType: number;

  constructor(private _libraryService: LibraryService, private router: Router) {}

  ngOnInit() {

    this._libraryService.getItems()
      .subscribe(res => {
        this.items = res.json();
        this.items.forEach(function(item,i){
          let d: string = item.publishedDate.day;
          if(d.toString().length == 1){
            d = "0"+ d;
          }
          item.publishedDate.day = d;
          let m: string = item.publishedDate.month;
          if(m.toString().length == 1){
            m = "0"+ m;
          }
          item.publishedDate.month = m;
        })
        console.log(this.items)
      }),
      error => {
        console.log(error)
      }


  }

  edit(item: any, index: any) {
    this.router.navigate(['./library-item', { isbn: item.isbn, action: 'edit' }]);
  }

  delete(id: string,i: number) {
    this._libraryService.deleteItem(id)
    .subscribe(res => {
      this.itemType = res.json().itemType;
        if (this.itemType == 1) {
          this.deleteMessage = "The book has been deleted";
        } else {
          this.deleteMessage = "The DVD has been deleted";
        }
        //remove from front end list
        this.items.splice(i,1);

        //display free spaces
        this.freeSpace = res.json().spaceLeft;
    }),
    error => {
      console.log(error)
    }
  }

  borrow(id: string,i: number) {
  }

}
