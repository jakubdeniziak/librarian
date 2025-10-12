import {Routes} from "@angular/router";
import {BookListComponent} from "@features/book/components/book-list/book-list.component";
import {BookNewComponent} from "@features/book/components/book-new/book-new.component";
import {BookDeleteComponent} from "@features/book/components/book-delete/book-delete.component";
import {BookDetailsComponent} from "@features/book/components/book-details/book-details.component";
import {BookEditComponent} from "@features/book/components/book-edit/book-edit.component";
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
