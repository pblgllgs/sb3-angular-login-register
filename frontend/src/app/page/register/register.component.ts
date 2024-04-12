import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/services/models';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}
  registerRequest: RegistrationRequest = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
  };
  errorMsg: Array<string> = [];
  login() {
    this.router.navigate(['login']);
  }
  register() {
    this.errorMsg = [];
    this.authService
      .register({
        body: this.registerRequest,
      })
      .subscribe({
        next: (res) => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          console.log(err);
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error.errorMsg);
          }
        },
      });
  }
}
