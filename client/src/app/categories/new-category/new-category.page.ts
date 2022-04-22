import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Security } from 'src/app/_models/security';
import { SecurityService } from 'src/app/_services/security.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.page.html',
  styleUrls: ['./new-category.page.scss'],
})
export class NewCategoryPage implements OnInit {

  constructor(
    public categoryService: SecurityService,
    private router: Router,
    private loadingCtrl: LoadingController) { }
  ngOnInit() {
    
  }

  onSubmit(form: NgForm): void{
    if (!form.valid){
      return;
    }

    this.loadingCtrl.create({
      message: 'Creating category...'
    })
    .then(loadingEl => {
      loadingEl.present();
      const name = form.value.name;
      const description = form.value.description;
      const currency = form.value.currency;
      const exchangeRate = form.value.exchangeRate;
      const faceValue = form.value.faceValue;
      const interest = form.value.interest;
      const fixedInterest = form.value.fixedInterest;

      const model = {name, description, currency, exchangeRate, faceValue,
      interest, fixedInterest}
      console.log(model);

      this.categoryService
        .addSecurity(
          model as Security
        )
        .subscribe(() => {
          loadingEl.dismiss();
          form.reset();
          this.router.navigate(['/categories/discover']);
        });
    });

    
  }

}
