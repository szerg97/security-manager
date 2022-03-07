import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuController, NavController } from '@ionic/angular';
import { Distributor } from 'src/app/_models/distributor';
import { DistributorService } from 'src/app/_services/distributor.service';

@Component({
  selector: 'app-distributor-details',
  templateUrl: './distributor-details.page.html',
  styleUrls: ['./distributor-details.page.scss'],
})
export class DistributorDetailsPage implements OnInit {

  distributor: Distributor;

  constructor(
    private service: DistributorService,
    private navCtrl: NavController,
    private activatedRoute: ActivatedRoute,
    private menuCtrl: MenuController) {
      this.menuCtrl.enable(false, 'm1');
    }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const categoryId = paramMap.get('id');
      this.getCategory(categoryId);
    });
  }

  getCategory(id: string){
    this.service.getDistributor(id).subscribe(response => {
      this.distributor = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }
}
