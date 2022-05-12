import { Component, OnInit, ViewChild } from '@angular/core';
import {
  Chart,
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip
} from 'chart.js';
import { KeycloakService } from 'keycloak-angular';
import { Portfolio } from '../_models/portfolio';
import { User } from '../_models/user';
import { AuthService } from '../_services/auth.service';
import { PortfolioService } from '../_services/portfolio.service';

Chart.register(
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip
);

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  @ViewChild('pieChart', {static: false}) pieChart;

  pie: any;
  pieColorArray: any;

  portfolio: Portfolio;
  user: User;
  isAdmin: boolean;

  constructor(
    private portfolioService: PortfolioService,
    private keycloakService: KeycloakService,
    private authService: AuthService
    ) { }

  ngOnInit() {
    this.isAdmin = this.authService.getUserRoles.includes('gsec');
    this.getUserDetails();
  }

  loadPortfolio(){
    this.portfolioService.fetchSelfPortfolio().subscribe(response => {
      this.portfolio = response;
      console.log(this.portfolio);
      this.createPieChart();
    });
  }

  ionViewWillEnter() {
    if (!this.isAdmin){
      this.loadPortfolio();
    }
  }

  buildArray(): number[]{
    const arr = [];
    for (let i = 0; i < this.portfolio.transactions.length; i++){
      arr.push(this.portfolio.transactions[i].denomination);
    }
    return arr;
  }

  createPieChart(){
    this.pie = new Chart(this.pieChart.nativeElement, {
      type: 'doughnut',
      data: {
          datasets: [{
            label: 'Portfolio',
            data: this.buildArray(),
            backgroundColor: [
              'rgb(0, 255, 150)',
              'rgb(255, 150, 0)',
              'rgb(255, 0, 150)',
            ],
            hoverOffset: 4,
          }],
          labels: [this.portfolio.transactions[0].securityName, this.portfolio.transactions[1].securityName, this.portfolio.transactions[2].securityName],
      },
      options: {
      }
  });
  }

  ionViewDidLeave(){
    this.pie.destroy();
  }

  getUserDetails(){
    this.keycloakService.getKeycloakInstance().loadUserInfo()
      .then(data => {
        this.user = data as User;
      });
  }
}
