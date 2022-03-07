import { Component } from '@angular/core';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { User } from './_models/user';
import { AuthService } from './_services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  user: User;

  constructor(
    private keycloakService: KeycloakService,
    private authService: AuthService
    ){
    this.getUserDetails();
    this.setRoles();
  }

  onLogout(){
    this.keycloakService.logout();
  }

  getUserDetails(){
    this.keycloakService.getKeycloakInstance().loadUserInfo()
      .then(data => {
        console.log(data);
        this.user = data as User;
      });
  }

  setRoles(){
    const roles = this.keycloakService.getKeycloakInstance().tokenParsed.resource_access.account.roles;
    this.authService.setUserRoles(roles);
  }
}
