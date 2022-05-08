import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.page.html',
  styleUrls: ['./categories.page.scss'],
})
export class CategoriesPage implements OnInit {

  isAdmin: boolean;

  constructor(private authService: AuthService) {
    this.isAdmin = this.authService.getUserRoles.includes('gsec'); 
  }

  ngOnInit() {
  }
}
