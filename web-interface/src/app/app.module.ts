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
import {LibraryListComponent} from "./library/view/library-list/library-list.component";
import {LibraryService} from "./library/service/library.service";
import {AuthorDetailsComponent} from "./author/view/author-details/author-details.component";
import {PublisherDetailsComponent} from "./publisher/view/publisher-details/publisher-details.component";
import {LibraryDetailsComponent} from "./library/view/library-details/library-details.component";
import {AuthorNewComponent} from "./author/view/author-new/author-new.component";
import {FormsModule} from "@angular/forms";
import {PublisherNewComponent} from "./publisher/view/publisher-new/publisher-new.component";
import {BookDetailsComponent} from "./book/view/book-details/book-details.component";
import {BookNewComponent} from "./book/view/book-new/book-new.component";
import {AuthorDeleteComponent} from "./author/view/author-delete/author-delete.component";
import {AuthorEditComponent} from "./author/view/author-edit/author-edit.component";
import {PublisherDeleteComponent} from "./publisher/view/publisher-delete/publisher-delete.component";
import {PublisherEditComponent} from "./publisher/view/publisher-edit/publisher-edit.component";
import {BookDeleteComponent} from "./book/view/book-delete/book-delete.component";
import {BookEditComponent} from "./book/view/book-edit/book-edit.component";

@NgModule({
    declarations: [
        AppComponent,
        NavComponent,
        HeaderComponent,
        FooterComponent,
        HomeComponent,
        AuthorListComponent,
        AuthorDetailsComponent,
        AuthorNewComponent,
        AuthorDeleteComponent,
        AuthorEditComponent,
        BookListComponent,
        BookDetailsComponent,
        BookNewComponent,
        BookDeleteComponent,
        BookEditComponent,
        PublisherListComponent,
        PublisherDetailsComponent,
        PublisherNewComponent,
        PublisherDeleteComponent,
        PublisherEditComponent,
        LibraryListComponent,
        LibraryDetailsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [
        AuthorService,
        BookService,
        PublisherService,
        LibraryService
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule { }
