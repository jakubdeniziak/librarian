import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "./app/app.component";
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {AuthorService} from "./app/author/service/author.service";
import {BookService} from "./app/book/service/book.service";
import {PublisherService} from "./app/publisher/service/publisher.service";
import {LibraryService} from "./app/library/service/library.service";
import {routes} from "./app/app-routing.module";
import {authInterceptor} from "./app/core/interceptors/auth-interceptor";

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])),
    provideRouter(routes),
    AuthorService,
    BookService,
    PublisherService,
    LibraryService,
  ]
}).catch(err => console.error(err));
