import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BookListComponent} from "./book/view/book-list/book-list.component";
import {HomeComponent} from "./home/home.component";
import {AuthorListComponent} from "./author/view/author-list/author-list.component";
import {PublisherListComponent} from "./publisher/view/publisher-list/publisher-list.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'authors', component: AuthorListComponent },
    { path: 'books', component: BookListComponent },
    { path: 'publishers', component: PublisherListComponent }
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