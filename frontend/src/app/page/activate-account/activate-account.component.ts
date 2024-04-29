import {Component} from '@angular/core';
import {Router} from '@angular/router';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss'],
})
export class ActivateAccountComponent {
  message: string = '';
  isOk: boolean = true;
  submitted: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {

  }

  onCodeCompleted(code: string) {
    this.confirmationAccount(code);
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  private confirmationAccount(code: string) {
    this.authService.validate({code})
      .subscribe({
        next: () => {
          this.message = 'Your account has been successfully activated.\nNow you can login in';
          this.submitted = true;
        },
        error: () => {
          this.message = 'The token has been expired.';
          this.submitted = true;
          this.isOk = false;
        }

      })
  }
}
