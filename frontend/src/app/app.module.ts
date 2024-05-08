import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './page/login/login.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './page/home/home.component';
import { ActivateAccountComponent } from './page/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import { RegisterComponent } from './page/register/register.component';
import { HttpTokenInterceptor } from './services/interceptor/http-token.interceptor';
import {KeycloakService} from "./services/keycloak/keycloak.service";

export function kcFactory(kcService: KeycloakService){
  return () => kcService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    ActivateAccountComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        CodeInputModule,
    ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
