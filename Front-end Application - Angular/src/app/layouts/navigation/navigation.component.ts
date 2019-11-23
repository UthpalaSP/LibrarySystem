import { Component, OnInit, Injectable, Input } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
//import { Subscription } from 'rxjs/Subscription';
//import 'metismenu';
//import 'jquery-slimscroll';
import { NavigationService } from './navigation.service';
declare var jQuery: any;
//import { TokenManager } from '../../../components/auth/tokenManager';
//import { NavigationManager } from '../../../components/auth/navigationManager';
//import { AuthorizeType } from '../../../shared/authorizeType';



@Component({
    selector: 'navigation',
    templateUrl: 'navigation.template.html',
    providers: [NavigationService]
})

@Injectable()
export class NavigationComponent {

    constructor(private router: Router) {   }

    // ngAfterViewInit() {
    //     jQuery('#side-menu').metisMenu();

    //     if (jQuery("body").hasClass('fixed-sidebar')) {
    //         jQuery('.sidebar-collapse').slimscroll({
    //             height: '100%'
    //         })
    //     }
    // }

    // ngOnInit() {
    //     console.log("Nvaigation component init called.................");
    // }

    // activeRoute(routename: string): boolean {
    //     return this.router.url.indexOf(routename) > -1;
    // }

}
