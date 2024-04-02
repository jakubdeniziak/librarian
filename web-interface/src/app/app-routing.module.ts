import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BookListComponent} from "./book/view/book-list/book-list.component";
import {HomeComponent} from "./home/home.component";
import {AuthorListComponent} from "./author/view/author-list/author-list.component";
import {PublisherListComponent} from "./publisher/view/publisher-list/publisher-list.component";
import {LibraryListComponent} from "./library/view/library-list/library-list.component";
import {AuthorDetailsComponent} from "./author/view/author-details/author-details.component";
import {PublisherDetailsComponent} from "./publisher/view/publisher-details/publisher-details.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'authors', component: AuthorListComponent },
    { path: 'authors/:uuid', component: AuthorDetailsComponent },
    { path: 'books', component: BookListComponent },
    { path: 'publishers', component: PublisherListComponent },
    { path: 'publishers/:uuid', component: PublisherDetailsComponent },
    { path: 'libraries', component: LibraryListComponent }
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
