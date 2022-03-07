import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Category } from 'src/app/_models/category';
import { CategoryService } from 'src/app/_services/category.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.page.html',
  styleUrls: ['./new-category.page.scss'],
})
export class NewCategoryPage implements OnInit {

  constructor(
    public categoryService: CategoryService,
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
      const model = {name: name, description: description}
      console.log(model);

      this.categoryService
        .addCategory(
          {
            name,
            description
          } as Category
        )
        .subscribe(() => {
          loadingEl.dismiss();
          form.reset();
          this.router.navigate(['/categories/discover']);
        });
    });

    
  }

}
