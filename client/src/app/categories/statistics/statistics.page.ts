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
import { Stat1 } from 'src/app/_models/stat1';
import { Stat2 } from 'src/app/_models/stat2';
import { SecurityService } from 'src/app/_services/security.service';
import { StatService } from 'src/app/_services/stat.service';
import { TransactionService } from 'src/app/_services/transaction.service';

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
  selector: 'app-statistics',
  templateUrl: './statistics.page.html',
  styleUrls: ['./statistics.page.scss'],
})
export class StatisticsPage implements OnInit {

  @ViewChild('barChart') barChart;
  @ViewChild('pieChart') pieChart;

  bars: any;
  barsColorArray: any;

  pie: any;
  pieColorArray: any;

  stats1: Stat1[];
  stats2: Stat2;

  constructor(
    private statService: StatService
    ) { }

  ionViewDidEnter() {
    this.loadStats1();
    this.loadStats2();
  }

  loadStats1(){
    this.statService.fetchStats1().subscribe(result => {
      this.stats1 = result;
      this.createBarChart();
    });
  }

  loadStats2(){
    this.statService.fetchStats2().subscribe(result => {
      this.stats2 = result;
      this.createPieChart();
    });
  }

  getValues(map:  Map<number, number>){
    const arr = [];
    Object.keys(map).forEach(key=>{
       arr.push(map[key])
    });

    return arr;
  }

  createPieChart(){
    this.pie = new Chart(this.pieChart.nativeElement, {
      type: 'doughnut',
      data: {
          datasets: [{
            label: 'Age distribution',
            data: [this.stats2.to25, this.stats2.from25to29, this.stats2.from30to39, this.stats2.from40to59, this.stats2.from60],
            backgroundColor: [
              'rgb(255, 99, 132)',
              'rgb(54, 162, 235)',
              'rgb(255, 205, 86)',
              'rgb(0, 255, 86)',
              'rgb(150, 10, 255)'
            ],
            hoverOffset: 4,
          }],
          labels: ['Under 25 years', '25 - 30 years', '30 - 40 years', '40 - 60', 'Above 60 years'],
      },
      options: {
      }
  });
  }

  createBarChart() {
    this.bars = new Chart(this.barChart.nativeElement, {
    type: 'line',
    data: {
        datasets: [{
            label: this.stats1[0].securityName,
            data: this.getValues(this.stats1[0].numberOfTransactions),
            backgroundColor: 'rgb(38, 194, 129)',
            tension: 0.4
        },
        {
          label: this.stats1[1].securityName,
          data: this.getValues(this.stats1[1].numberOfTransactions),
          backgroundColor: 'rgb(255, 0, 0)',
          tension: 0.4
      },
      {
        label: this.stats1[2].securityName,
        data: this.getValues(this.stats1[2].numberOfTransactions),
        backgroundColor: 'rgb(255, 0, 255)',
        tension: 0.4
    }
    ],
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
    },
    options: {
        scales: {
            y: {
                suggestedMin: 0,
                suggestedMax: 300
            }
        }
    }
});
  }

  ngOnInit() {
  }

}
