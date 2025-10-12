import {Routes} from "@angular/router";
import {PublisherListComponent} from "@features/publisher/components/publisher-list/publisher-list.component";
import {PublisherNewComponent} from "@features/publisher/components/publisher-new/publisher-new.component";
import {PublisherDeleteComponent} from "@features/publisher/components/publisher-delete/publisher-delete.component";
import {PublisherDetailsComponent} from "@features/publisher/components/publisher-details/publisher-details.component";
import {PublisherEditComponent} from "@features/publisher/components/publisher-edit/publisher-edit.component";
import {adminGuard} from "@core/auth/guards/admin.guard";

export const publisherRoutes: Routes = [
  {
    path: 'publishers',
    children: [
      {path: '', component: PublisherListComponent},
      {path: 'add', component: PublisherNewComponent, canActivate: [adminGuard]},
      {path: 'delete', component: PublisherDeleteComponent, canActivate: [adminGuard]},
      {path: ':uuid', component: PublisherDetailsComponent},
      {path: ':uuid/edit', component: PublisherEditComponent, canActivate: [adminGuard]},
    ],
  },
];
