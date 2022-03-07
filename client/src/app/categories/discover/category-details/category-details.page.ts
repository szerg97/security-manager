import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuController, NavController } from '@ionic/angular';
import { Category } from 'src/app/_models/category';
import { CategoryService } from 'src/app/_services/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.page.html',
  styleUrls: ['./category-details.page.scss'],
})
export class CategoryDetailsPage implements OnInit {
  
  category: Category;

  constructor(
    private service: CategoryService,
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
    this.service.getCategory(id).subscribe(response => {
      this.category = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }

  onPurchase(){
    this.navCtrl.navigateBack('/categories/discover');
  }
}
