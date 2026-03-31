import {Routes} from "@angular/router";
import {HomeComponent} from "@core/layout/home/home.component";
import {DataComponent} from "@features/data/components/data-manage/data-manage.component";
import {authorRoutes} from "@features/author/author.routes";
import {publisherRoutes} from "@features/publisher/publishers.routes";
import {libraryRoutes} from "@features/library/library.routes";
import {adminGuard} from "@core/auth/guards/admin.guard";
import {UnauthorizedComponent} from "@core/auth/components/unauthorized/unauthorized.component";
import {bookRoutes} from "@features/book/book.routes";
import {LoginComponent} from "@core/auth/components/login/login.component";
import {RegisterComponent} from "@core/auth/components/register/register.component";
import {NotFoundComponent} from "@core/layout/not-found/not-found.component";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'data', component: DataComponent, canActivate: [adminGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'unauthorized', component: UnauthorizedComponent},
  {path: '', children: authorRoutes},
  {path: '', children: bookRoutes},
  {path: '', children: publisherRoutes},
  {path: '', children: libraryRoutes},
  {path: '**', component: NotFoundComponent},
];
