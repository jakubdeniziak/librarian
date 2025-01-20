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
import {AuthorDeleteComponent} from "./author/view/author-delete/author-delete.component";
import {AuthorEditComponent} from "./author/view/author-edit/author-edit.component";
import {PublisherDeleteComponent} from "./publisher/view/publisher-delete/publisher-delete.component";
import {PublisherEditComponent} from "./publisher/view/publisher-edit/publisher-edit.component";
import {BookDeleteComponent} from "./book/view/book-delete/book-delete.component";
import {BookEditComponent} from "./book/view/book-edit/book-edit.component";
import {LibraryNewComponent} from "./library/view/library-new/library-new.component";
import {LibraryDeleteComponent} from "./library/view/library-delete/library-delete.component";
import {LibraryAddBookComponent} from "./library/view/library-add-book/library-add-book.component";
import {DataComponent} from "./data/view/data.component";
import {LoginComponent} from "./login/view/login.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'authors', component: AuthorListComponent },
    { path: 'authors/add', component: AuthorNewComponent },
    { path: 'authors/delete', component: AuthorDeleteComponent },
    { path: 'authors/:uuid', component: AuthorDetailsComponent },
    { path: 'authors/:uuid/edit', component: AuthorEditComponent },
    { path: 'books', component: BookListComponent },
    { path: 'books/add', component: BookNewComponent },
    { path: 'books/delete', component: BookDeleteComponent },
    { path: 'books/:uuid', component: BookDetailsComponent },
    { path: 'books/:uuid/edit', component: BookEditComponent },
    { path: 'publishers', component: PublisherListComponent },
    { path: 'publishers/add', component: PublisherNewComponent },
    { path: 'publishers/delete', component: PublisherDeleteComponent },
    { path: 'publishers/:uuid', component: PublisherDetailsComponent },
    { path: 'publishers/:uuid/edit', component: PublisherEditComponent },
    { path: 'libraries', component: LibraryListComponent },
    { path: 'libraries/add', component: LibraryNewComponent },
    { path: 'libraries/delete', component: LibraryDeleteComponent },
    { path: 'libraries/:uuid', component: LibraryDetailsComponent },
    { path: 'libraries/:uuid/add-book', component: LibraryAddBookComponent },
    { path: 'data', component: DataComponent },
    { path: 'login', component: LoginComponent }
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
