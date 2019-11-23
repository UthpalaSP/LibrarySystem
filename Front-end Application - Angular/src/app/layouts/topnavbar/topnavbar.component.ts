import { Component, OnInit } from '@angular/core';
import { smoothlyMenu } from '../app.helpers';
import { Router } from "@angular/router";
declare var jQuery: any;

@Component({
    selector: 'topnavbar',
    templateUrl: 'topnavbar.template.html'
})
export class TopNavbarComponent implements OnInit {
    constructor(private router: Router) {}

    ngOnInit() {
        
    }

    toggleNavigation(): void {
        jQuery("body").toggleClass("mini-navbar");
        smoothlyMenu();
    }

}
