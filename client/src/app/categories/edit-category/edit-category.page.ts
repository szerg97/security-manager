import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Security } from 'src/app/_models/security';
import { SecurityService } from 'src/app/_services/security.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.page.html',
  styleUrls: ['./edit-category.page.scss'],
})
export class EditCategoryPage implements OnInit, OnDestroy {

  category: Security;
  isLoading = false;
  private categorySub: Subscription;

  constructor(
    private categoryService: SecurityService,
    private activatedRoute: ActivatedRoute,
    private loadingCtrl: LoadingController,
    private router: Router
    ) { }

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
    this.categoryService.getSecurity(id).subscribe(response => {
      this.category = response;
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
        message: 'Updating category...'
      })
      .then(loadingEl => {
        loadingEl.present();
        this.categoryService
          .updateSecurity(
            this.category.id,
            form.value.name,
            form.value.description
          )
          .subscribe(() => {
            loadingEl.dismiss();
            form.reset();
            this.router.navigate([`/categories/discover/${this.category.id}`]);
          });
      });
  }

  ngOnDestroy(){
    if (this.categorySub) {
      this.categorySub.unsubscribe();
    }
  }

}
