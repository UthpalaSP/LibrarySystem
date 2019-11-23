import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
//import {Ng2BootstrapModule} from 'ngx-bootstrap';

import {IboxtoolsComponent} from "./iboxtools.component";

@NgModule({
  declarations: [IboxtoolsComponent],
  imports: [BrowserModule],//, Ng2BootstrapModule.forRoot()
  exports     : [IboxtoolsComponent],
})

export class IboxtoolsModule {}
