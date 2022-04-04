import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Issuer } from 'src/app/_models/issuer';
import { DistributorService } from 'src/app/_services/distributor.service';

@Component({
  selector: 'app-new-distributor',
  templateUrl: './new-distributor.page.html',
  styleUrls: ['./new-distributor.page.scss'],
})
export class NewDistributorPage implements OnInit {

  constructor(
    public distributorService: DistributorService,
    private router: Router,
    private loadingCtrl: LoadingController) { }
  ngOnInit() {
    
  }

  onSubmit(form: NgForm): void{
    if (!form.valid){
      return;
    }

    this.loadingCtrl.create({
      message: 'Creating distributor...'
    })
    .then(loadingEl => {
      loadingEl.present();
      const name = form.value.name;
      const email = form.value.email;
      const phone = form.value.phone;
      const status = form.value.status;

      this.distributorService
        .addDistributor(
          {
            name,
            email,
            phone,
            status
          } as Issuer
        )
        .subscribe(() => {
          loadingEl.dismiss();
          form.reset();
          this.router.navigate(['/distributors']);
        });
    });

    
  }

}
