import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "./app/app.component";
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideRouter} from "@angular/router";
import {BookService} from "./app/book/service/book.service";
import {routes} from "./app/app.routes";
import {authInterceptor} from "@core/interceptors/auth-interceptor";

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])),
    provideRouter(routes),
    BookService,
  ]
}).catch(err => console.error(err));
