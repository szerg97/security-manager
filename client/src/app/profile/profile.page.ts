import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Portfolio } from '../_models/portfolio';
import { User } from '../_models/user';
import { PortfolioService } from '../_services/portfolio.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  portfolio: Portfolio;
  user: User;

  constructor(
    private portfolioService: PortfolioService,
    private keycloakService: KeycloakService
    ) { }

  ngOnInit() {
    this.getUserDetails();
    this.loadPortfolio();
  }

  loadPortfolio(){
    this.portfolioService.fetchSelfPortfolio().subscribe(response => {
      this.portfolio = response;
    });
  }

  ionViewWillEnter() {
    this.loadPortfolio();
  }

  getUserDetails(){
    this.keycloakService.getKeycloakInstance().loadUserInfo()
      .then(data => {
        this.user = data as User;
      });
  }
}
