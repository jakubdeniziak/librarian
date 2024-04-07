import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BookListComponent} from "./book/view/book-list/book-list.component";
import {HomeComponent} from "./home/home.component";
import {AuthorListComponent} from "./author/view/author-list/author-list.component";
import {PublisherListComponent} from "./publisher/view/publisher-list/publisher-list.component";
import {LibraryListComponent} from "./library/view/library-list/library-list.component";
import {AuthorDetailsComponent} from "./author/view/author-details/author-details.component";
import {PublisherDetailsComponent} from "./publisher/view/publisher-details/publisher-details.component";
import {LibraryDetailsComponent} from "./library/view/library-details/library-details.component";
import {AuthorNewComponent} from "./author/view/author-new/author-new.component";
import {PublisherNewComponent} from "./publisher/view/publisher-new/publisher-new.component";
import {BookDetailsComponent} from "./book/view/book-details/book-details.component";
import {BookNewComponent} from "./book/view/book-new/book-new.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'authors', component: AuthorListComponent },
    { path: 'authors/add', component: AuthorNewComponent },
    { path: 'authors/:uuid', component: AuthorDetailsComponent },
    { path: 'books', component: BookListComponent },
    { path: 'books/add', component: BookNewComponent },
    { path: 'books/:uuid', component: BookDetailsComponent },
    { path: 'publishers', component: PublisherListComponent },
    { path: 'publishers/add', component: PublisherNewComponent },
    { path: 'publishers/:uuid', component: PublisherDetailsComponent },
    { path: 'libraries', component: LibraryListComponent },
    { path: 'libraries/:uuid', component: LibraryDetailsComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule { }
