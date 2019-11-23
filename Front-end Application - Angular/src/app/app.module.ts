import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BasicLayoutComponent } from './layouts/layouts/basicLayout.component';
import { BlankLayoutComponent } from './layouts/layouts/blankLayout.component';

import { AppComponent } from './app.component';
import { NavigationComponent } from './layouts/navigation/navigation.component';
import { TopNavbarComponent } from './layouts/topnavbar/topnavbar.component';
import { HomeComponent } from './home/home.component';
import { LibraryItemComponent } from './library-item/library-item.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import 'hammerjs';
import { FooterComponent } from './layouts/footer/footer.component';
import { FormsModule } from '@angular/forms';
import { LibraryItemListComponent } from './library-item/library-item-list.component';
import { DeleteItemComponent } from './delete-item/delete-item.component';
import { BorrowItemComponent } from './borrow-item/borrow-item.component';
import { ReturnItemComponent } from './return-item/return-item.component';
import { ReportComponent } from './report/report.component';
import { ReserveItemComponent } from './reserve-item/reserve-item.component';
import { Config } from './app.config';
import { HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    AppComponent,
    BasicLayoutComponent,
    BlankLayoutComponent,
    NavigationComponent,
    TopNavbarComponent,
    FooterComponent,
    HomeComponent,
    LibraryItemComponent,
    LibraryItemListComponent,
    DeleteItemComponent,
    BorrowItemComponent,
    ReturnItemComponent,
    ReportComponent,
    ReserveItemComponent
  ],
  imports: [
    HttpModule,
    BrowserModule,
    MaterialModule,
    FormsModule,
    BrowserAnimationsModule,
    Config,
    RouterModule.forRoot([
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      {
        path: '', component: BasicLayoutComponent,
        children: [
          { path: 'home', component: HomeComponent },
          { path: 'library-item', component: LibraryItemComponent },
          { path: 'library-item-list', component: LibraryItemListComponent },
          { path: 'delete-item', component: DeleteItemComponent },
          { path: 'borrow-item', component: BorrowItemComponent },
          { path: 'return-item', component: ReturnItemComponent },
          { path: 'report', component: ReportComponent },
          { path: 'reserve-item', component: ReserveItemComponent }
        ]
      }
    ])
  ],
  providers: [Config],
  bootstrap: [AppComponent]
})
export class AppModule { }
