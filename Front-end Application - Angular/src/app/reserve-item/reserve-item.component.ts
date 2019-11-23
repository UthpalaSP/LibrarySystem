import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';

@Component({
  selector: 'reserve-item',
  templateUrl: './reserve-item.component.html'
})
export class ReserveItemComponent implements OnInit {

  freeSpace: any[] = [];
  borrowErrMessage: string = null;
  borrowSucMessage: string = null;
  isbn: string;

  constructor() { }

  ngOnInit() {
  }

  onSubmit(){
    //alreeady borrowed
    //set the date
    this.borrowErrMessage = "This item is already borrowed. It will be available on ";
    //ready to borrow
    //display when to return
    this.borrowSucMessage = "The borrow is successful";
  }

}
