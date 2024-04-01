import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BookListComponent } from "./book/view/book-list/book-list.component";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {BookService} from "./book/service/book.service";
import {NavComponent} from "./section/nav/nav.component";
import {FooterComponent} from "./section/footer/footer.component";
import {HeaderComponent} from "./section/header/header.component";
import {HomeComponent} from "./home/home.component";

@NgModule({
    declarations: [
        AppComponent,
        NavComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        BookListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        BookService
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule { }
