import {Routes} from "@angular/router";
import {BookListComponent} from "./view/book-list/book-list.component";
import {BookNewComponent} from "./view/book-new/book-new.component";
import {BookDeleteComponent} from "./view/book-delete/book-delete.component";
import {BookDetailsComponent} from "./view/book-details/book-details.component";
import {BookEditComponent} from "./view/book-edit/book-edit.component";
import {adminGuard} from "@core/auth/guards/admin.guard";

export const bookRoutes: Routes = [
  {
    path: 'books',
    children: [
      {path: '', component: BookListComponent},
      {path: 'add', component: BookNewComponent, canActivate: [adminGuard]},
      {path: 'delete', component: BookDeleteComponent, canActivate: [adminGuard]},
      {path: ':uuid', component: BookDetailsComponent},
      {path: ':uuid/edit', component: BookEditComponent, canActivate: [adminGuard]},
    ],
  },
];
