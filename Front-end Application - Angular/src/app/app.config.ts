import { Injectable, NgModule } from "@angular/core";

@NgModule({
    providers: [{
        provide: Config,
        useValue: Config
    }]
})

@Injectable()
export class Config{

    public apiUrl: string = "http://localhost:9000/";
}