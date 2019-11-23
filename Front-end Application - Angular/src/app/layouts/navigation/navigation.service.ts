import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
//import { Observable } from 'rxjs/Observable';
// import 'rxjs/add/observable/throw';
// import 'rxjs/add/operator/catch';
// import 'rxjs/add/operator/do';
// import 'rxjs/add/operator/map';

@Injectable()
export class NavigationService {

    constructor(private _http: Http) {    }

}