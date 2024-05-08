import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../services/services/authentication.service";
import {TokenService} from "../../../../services/token/token.service";
import {KeycloakService} from "../../../../services/keycloak/keycloak.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  username: string | null = '';

  ngOnInit(): void {
    const linkColor = document.querySelectorAll('.nav-link');
    linkColor.forEach((link) => {
      if (window.location.href.endsWith(link.getAttribute('href') || '')) {
        link.classList.add('active');
      }
      link.addEventListener('click', () => {
        linkColor.forEach((l) => l.classList.remove('active'));
        link.classList.add('active');
      });
    });
    this.username = this.keycloakService.profile?.username as string;
  }

  constructor(
    private router: Router,
    private keycloakService: KeycloakService
  ) {
  }

  async logout() {
    await this.keycloakService.logout()
  }
}
