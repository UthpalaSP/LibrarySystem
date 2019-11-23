import { Injectable } from "@angular/core";
import { Config } from "../app.config";
import { Observable } from "rxjs";
import { Http, Headers } from "@angular/http";
import { LibraryItem } from "../models/library-item";

@Injectable()
export class LibraryService {

    _libraryUrl: string;
    _headers: Headers;

    constructor(private _config: Config, private _http: Http) {

        this._libraryUrl = _config.apiUrl + "library";
        this._headers = new Headers();
        this._headers.append('Accept', 'application/json');
        this._headers.append('Content-Type', 'application/json');
        this._headers.append('Access-Control-Allow-Origin', '*');

    }

    getItems(): Observable<any> {
        return this._http.get(this._libraryUrl);
    }

    get(isbn: string): Observable<any>{
        return this._http.get(this._libraryUrl + "/" + isbn);
    }

    spaceLeft(): Observable<any> {
        return this._http.get(this._libraryUrl + "/create");
    }

    saveItem(item: LibraryItem): Observable<any> {
        return this._http.post(this._libraryUrl + "/create", JSON.stringify(item), { headers: this._headers });
    }

    deleteItem(id: string): Observable<any> {
        return this._http.get(this._libraryUrl + "/delete/" + id);
    }

    deleteIsbn(id: string): Observable<any>{
        return this._http.get(this._libraryUrl + "/deleteIsbn/"+ id);
    }

    borrowIsbn(item: LibraryItem): Observable<any>{
        return this._http.post(this._libraryUrl + "/borrow", JSON.stringify(item), { headers: this._headers});
    }

    returnIsbn(isbn: string): Observable<any>{
        return this._http.get(this._libraryUrl + "/return/" + isbn);
    }

    report(): Observable<any>{
        return this._http.get(this._libraryUrl + "/report");
    }

    private handleError(err: any) {
        console.error(err.message);
        return Observable.throw(err.message);
    }
}