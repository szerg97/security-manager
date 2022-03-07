import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { finalize } from 'rxjs/operators';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.page.html',
  styleUrls: ['./auth.page.scss'],
})
export class AuthPage implements OnInit {
  isLoading = false;
  isLogin = true;

  constructor(
    private loadingCtrl: LoadingController,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit() {
  }

  onSwitchAuthMode(){
    this.isLogin = !this.isLogin;
  }

  onSubmit(form: NgForm){
    if (!form.valid){
      return;
    }
    const firstName = form.value.firstName;
    const lastName = form.value.lastName;
    const username = form.value.username;
    const password = form.value.password;

    if (this.isLogin){
      this.isLoading = true;
      this.authService.login({username: username, password: password});
      this.loadingCtrl.create({keyboardClose: true, message: 'Logging in...'}).then(loadingElement => {
        loadingElement.present();
        setTimeout(() => {
          this.isLoading = false;
          loadingElement.dismiss();
          this.router.navigateByUrl('/categories/discover');
        },1500);
      });
    }
    else{
      this.isLoading = true;
      this.authService.signup(firstName, lastName, username, password).subscribe(response => {
        this.router.navigateByUrl('/auth');
      }, error => {
        console.log(error);
      });
    }
  }
}
