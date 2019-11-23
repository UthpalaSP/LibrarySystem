import { Component, OnInit } from '@angular/core';
import { LibraryItem } from '../models/library-item';
import { LibraryService } from '../services/library.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'library-item',
  templateUrl: './library-item.component.html',
  providers: [LibraryService]
  // styleUrls: ['./library-item.component.css']
})
export class LibraryItemComponent implements OnInit {

  model: LibraryItem = new LibraryItem;
  loaderEnabled: boolean = false;
  componetTitle: string = "";
  freeSpace: any[] = [];
  noSpaceMessage: string = null;
  successMessage: string = null;
  isbn: string;
  action: string;

  constructor(private _libraryService: LibraryService, private route: ActivatedRoute) {}

  ngOnInit() {

    this.route.params.subscribe(params => (this.isbn = params.isbn, this.action = params.action));
        if (this.action == 'edit') {
            this.componetTitle = "Edit Item";
            this._libraryService.get(this.isbn)
                .subscribe(res => {
                    this.model = res.json();
                    console.log(res)
                },
                error => {
                });
        }else{
          this.componetTitle = "Add Library Item";
        }

    //get no of free slots left
    
    this._libraryService.spaceLeft()
      .subscribe(res => {
        this.freeSpace = res.json();
        console.log(this.freeSpace)

        let hasBookSpace = this.freeSpace[0] > 0;
        let hasDvdSpace = this.freeSpace[1] > 0;

        console.log(hasBookSpace + "," + hasDvdSpace)

        if (!hasBookSpace)
          this.noSpaceMessage = hasDvdSpace ? "There's not enough space for Books." : "There's not enough space for Books and DVDs.";
        else
          this.noSpaceMessage = hasDvdSpace ? null : "There's not enough space for DVDs.";//has enough space for both
      }),
      error => {
        console.log(error)
      }

    //default dropdown
    this.model.itemType = 1;
  }

  onSubmit() {
    this.resetMsgs();
    let hasSpace = this.freeSpace[this.model.itemType - 1] > 0;
    if (hasSpace)
      console.log("success")//save function
    else
      console.log("not enough space")//not enough space

    this._libraryService.saveItem(this.model)
      .subscribe(res => {
        console.log(res.json())
        if(res.json().status == "FAIL"){
          this.noSpaceMessage = res.json().message;
        }else{
          this.successMessage = res.json().message;
        }

        //scroll to top
        let scrollToTop = window.setInterval(() => {
          let pos = window.pageYOffset;
          if (pos > 0) {
              window.scrollTo(0, pos - 20); // how far to scroll on each step
          } else {
              window.clearInterval(scrollToTop);
          }
      }, 16);

      this.freeSpace[this.model.itemType - 1] = this.freeSpace[this.model.itemType - 1] -1;

      }),
      error => {
        console.log(error)
      }

    console.log(this.model)
  }

  resetMsgs(){
    this.noSpaceMessage = null;
    this.successMessage = null;
  }

}
