import {Routes} from "@angular/router";
import {AuthorListComponent} from "@features/author/components/author-list/author-list.component";
import {AuthorNewComponent} from "@features/author/components/author-new/author-new.component";
import {AuthorDeleteComponent} from "@features/author/components/author-delete/author-delete.component";
import {AuthorDetailsComponent} from "@features/author/components/author-details/author-details.component";
import {AuthorEditComponent} from "@features/author/components/author-edit/author-edit.component";
import {adminGuard} from "../../auth/admin.guard";

export const authorRoutes: Routes = [
  {
    path: 'authors',
    children: [
      {path: '', component: AuthorListComponent},
      {path: 'add', component: AuthorNewComponent, canActivate: [adminGuard]},
      {path: 'delete', component: AuthorDeleteComponent, canActivate: [adminGuard]},
      {path: ':uuid', component: AuthorDetailsComponent},
      {path: ':uuid/edit', component: AuthorEditComponent, canActivate: [adminGuard]},
    ],
  },
];
