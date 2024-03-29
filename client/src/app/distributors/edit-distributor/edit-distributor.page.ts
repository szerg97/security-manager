import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Issuer } from 'src/app/_models/issuer';
import { DistributorService } from 'src/app/_services/distributor.service';

@Component({
  selector: 'app-edit-distributor',
  templateUrl: './edit-distributor.page.html',
  styleUrls: ['./edit-distributor.page.scss'],
})
export class EditDistributorPage implements OnInit, OnDestroy {

  issuer: Issuer;
  isLoading = false;
  private distributorSub: Subscription;

  constructor(
    private distributorService: DistributorService,
    private activatedRoute: ActivatedRoute,
    private loadingCtrl: LoadingController,
    private router: Router
    ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const distributorId = paramMap.get('id');
      this.getCategory(distributorId);
    });
  }

  getCategory(id: string){
    this.distributorService.getDistributor(id).subscribe(response => {
      this.issuer = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }

  onSubmit(form: NgForm){
    if(!form.valid){
      return;
    }
    this.loadingCtrl
      .create({
        message: 'Updating issuer...'
      })
      .then(loadingEl => {
        loadingEl.present();
        this.distributorService
          .updateDistributor(
            this.issuer.id,
            form.value.name,
            form.value.email,
            form.value.phone,
            form.value.status,
          )
          .subscribe(() => {
            loadingEl.dismiss();
            form.reset();
            this.router.navigate([`/distributors/${this.issuer.id}`]);
          });
      });
  }

  ngOnDestroy(){
    if (this.distributorSub) {
      this.distributorSub.unsubscribe();
    }
  }

}