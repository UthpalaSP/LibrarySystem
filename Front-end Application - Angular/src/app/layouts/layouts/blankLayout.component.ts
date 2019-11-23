import { Component } from '@angular/core';
import { detectBody } from '../app.helpers';

//declare var jQuery:any;

@Component({
  selector: 'blank',
  templateUrl: 'blankLayout.template.html',
  host: {
    '(window:resize)': 'onResize()'
  }
})
export class BlankLayoutComponent {

  // public ngOnInit():any {
  //   detectBody();
  // }

  public onResize(){
    detectBody();
  }

}
