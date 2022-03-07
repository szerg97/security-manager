import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Category } from 'src/app/_models/category';
import { CategoryService } from 'src/app/_services/category.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.page.html',
  styleUrls: ['./edit-category.page.scss'],
})
export class EditCategoryPage implements OnInit, OnDestroy {

  category: Category;
  isLoading = false;
  private categorySub: Subscription;

  constructor(
    private categoryService: CategoryService,
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
    this.categoryService.getCategory(id).subscribe(response => {
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
          .updateCategory(
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
