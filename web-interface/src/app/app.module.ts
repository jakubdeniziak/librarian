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
import {AuthorListComponent} from "./author/view/author-list/author-list.component";
import {AuthorService} from "./author/service/author.service";
import {PublisherService} from "./publisher/service/publisher.service";
import {PublisherListComponent} from "./publisher/view/publisher-list/publisher-list.component";

@NgModule({
    declarations: [
        AppComponent,
        NavComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        AuthorListComponent,
        BookListComponent,
        PublisherListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        AuthorService,
        BookService,
        PublisherService
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule { }
