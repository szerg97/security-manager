import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
    ){
    this.getUserDetails();
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
}
