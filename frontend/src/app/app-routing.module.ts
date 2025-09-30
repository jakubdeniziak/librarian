import {Routes} from "@angular/router";
import {BookListComponent} from "./book/view/book-list/book-list.component";
import {AuthorListComponent} from "@features/author/components/author-list/author-list.component";
import {AuthorDetailsComponent} from "@features/author/components/author-details/author-details.component";
import {AuthorNewComponent} from "@features/author/components/author-new/author-new.component";
import {BookDetailsComponent} from "./book/view/book-details/book-details.component";
import {BookNewComponent} from "./book/view/book-new/book-new.component";
import {AuthorDeleteComponent} from "@features/author/components/author-delete/author-delete.component";
import {AuthorEditComponent} from "@features/author/components/author-edit/author-edit.component";
import {BookDeleteComponent} from "./book/view/book-delete/book-delete.component";
import {BookEditComponent} from "./book/view/book-edit/book-edit.component";
import {LoginComponent} from "./user/view/login/login.component";
import {RegisterComponent} from "./user/view/register/register.component";
import {adminGuard} from "./auth/admin.guard";
import {UnauthorizedComponent} from "./auth/unauthorized/unauthorized.component";
import {PublisherListComponent} from "@features/publisher/components/publisher-list/publisher-list.component";
import {PublisherNewComponent} from "@features/publisher/components/publisher-new/publisher-new.component";
import {PublisherDeleteComponent} from "@features/publisher/components/publisher-delete/publisher-delete.component";
import {PublisherDetailsComponent} from "@features/publisher/components/publisher-details/publisher-details.component";
import {PublisherEditComponent} from "@features/publisher/components/publisher-edit/publisher-edit.component";
import {HomeComponent} from "@core/layout/home/home.component";
import {LibraryListComponent} from "@features/library/components/library-list/library-list.component";
import {LibraryNewComponent} from "@features/library/components/library-new/library-new.component";
import {LibraryDeleteComponent} from "@features/library/components/library-delete/library-delete.component";
import {LibraryDetailsComponent} from "@features/library/components/library-details/library-details.component";
import {LibraryAddBookComponent} from "@features/library-book/components/library-add-book/library-add-book.component";
import {DataComponent} from "@features/data/components/data-manage/data-manage.component";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'authors', component: AuthorListComponent},
  {path: 'authors/add', component: AuthorNewComponent, canActivate: [adminGuard]},
  {path: 'authors/delete', component: AuthorDeleteComponent, canActivate: [adminGuard]},
  {path: 'authors/:uuid', component: AuthorDetailsComponent},
  {path: 'authors/:uuid/edit', component: AuthorEditComponent, canActivate: [adminGuard]},
  {path: 'books', component: BookListComponent},
  {path: 'books/add', component: BookNewComponent, canActivate: [adminGuard]},
  {path: 'books/delete', component: BookDeleteComponent, canActivate: [adminGuard]},
  {path: 'books/:uuid', component: BookDetailsComponent},
  {path: 'books/:uuid/edit', component: BookEditComponent, canActivate: [adminGuard]},
  {path: 'publishers', component: PublisherListComponent},
  {path: 'publishers/add', component: PublisherNewComponent, canActivate: [adminGuard]},
  {path: 'publishers/delete', component: PublisherDeleteComponent, canActivate: [adminGuard]},
  {path: 'publishers/:uuid', component: PublisherDetailsComponent},
  {path: 'publishers/:uuid/edit', component: PublisherEditComponent, canActivate: [adminGuard]},
  {path: 'libraries', component: LibraryListComponent},
  {path: 'libraries/add', component: LibraryNewComponent},
  {path: 'libraries/delete', component: LibraryDeleteComponent},
  {path: 'libraries/:uuid', component: LibraryDetailsComponent},
  {path: 'libraries/:uuid/add-book', component: LibraryAddBookComponent},
  {path: 'data', component: DataComponent, canActivate: [adminGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'unauthorized', component: UnauthorizedComponent},
];
