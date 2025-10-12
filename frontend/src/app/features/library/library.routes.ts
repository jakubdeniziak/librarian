import {Routes} from "@angular/router";
import {LibraryListComponent} from "@features/library/components/library-list/library-list.component";
import {LibraryNewComponent} from "@features/library/components/library-new/library-new.component";
import {LibraryDeleteComponent} from "@features/library/components/library-delete/library-delete.component";
import {LibraryDetailsComponent} from "@features/library/components/library-details/library-details.component";
import {LibraryAddBookComponent} from "@features/library-book/components/library-add-book/library-add-book.component";

export const libraryRoutes: Routes = [
  {
    path: 'libraries',
    children: [
      {path: '', component: LibraryListComponent},
      {path: 'add', component: LibraryNewComponent},
      {path: 'delete', component: LibraryDeleteComponent},
      {path: ':uuid', component: LibraryDetailsComponent},
      {path: ':uuid/add-book', component: LibraryAddBookComponent},
    ],
  },
];
