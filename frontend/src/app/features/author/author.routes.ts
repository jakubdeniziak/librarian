import {Routes} from "@angular/router";
import {AuthorListComponent} from "@features/author/components/author-list/author-list.component";
import {AuthorNewComponent} from "@features/author/components/author-new/author-new.component";
import {AuthorDetailsComponent} from "@features/author/components/author-details/author-details.component";
import {AuthorEditComponent} from "@features/author/components/author-edit/author-edit.component";
import {adminGuard} from "@core/auth/guards/admin.guard";

export const authorRoutes: Routes = [
  {
    path: 'authors',
    children: [
      {path: '', component: AuthorListComponent},
      {path: 'add', component: AuthorNewComponent, canActivate: [adminGuard]},
      {path: ':uuid', component: AuthorDetailsComponent},
      {path: ':uuid/edit', component: AuthorEditComponent, canActivate: [adminGuard]},
    ],
  },
];
