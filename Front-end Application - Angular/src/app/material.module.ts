import {MatButtonModule, MatCheckboxModule, MatGridListModule, MatSidenavModule, MatToolbar, MatToolbarModule, MatMenuModule, MatIconModule} from '@angular/material';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    MatButtonModule, 
    MatCheckboxModule, 
    MatGridListModule, 
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule
  ],
  exports: [
    MatButtonModule, 
    MatCheckboxModule, 
    MatGridListModule, 
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule
  ],
})
export class MaterialModule { }