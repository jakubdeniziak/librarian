import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "./app/app.component";
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {BookService} from "./app/book/service/book.service";
import {LibraryService} from "./app/library/service/library.service";
import {routes} from "./app/app-routing.module";
import {authInterceptor} from "./app/core/interceptors/auth-interceptor";

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])),
    provideRouter(routes),
    BookService,
    LibraryService,
  ]
}).catch(err => console.error(err));
