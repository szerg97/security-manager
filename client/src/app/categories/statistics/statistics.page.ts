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

  bars: any;
  colorArray: any;

  stats1: Stat1[];

  constructor(
    private statService: StatService
    ) { }

  ionViewDidEnter() {
    this.loadStats1();
  }

  loadStats1(){
    this.statService.fetchStats().subscribe(result => {
      this.stats1 = result;
      this.createBarChart();
    });
  }

  getValues(map:  Map<number, number>){
    const arr = [];
    Object.keys(map).forEach(key=>{
       arr.push(map[key])
    });

    return arr;
  }

  createBarChart() {
    this.bars = new Chart(this.barChart.nativeElement, {
    type: 'line',
    data: {
        datasets: [{
            label: this.stats1[0].securityName,
            data: this.getValues(this.stats1[0].numberOfTransactions),
            backgroundColor: 'rgb(38, 194, 129)'
        },
        {
          label: this.stats1[1].securityName,
          data: this.getValues(this.stats1[1].numberOfTransactions),
          backgroundColor: 'rgb(255, 0, 0)'
      },
      {
        label: this.stats1[2].securityName,
        data: this.getValues(this.stats1[2].numberOfTransactions),
        backgroundColor: 'rgb(255, 0, 255)'
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
