import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoadingController, ModalController } from '@ionic/angular';
import { Message } from 'src/app/_models/message';
import { MessageService } from 'src/app/_services/message.service';

@Component({
  selector: 'app-new-message',
  templateUrl: './new-message.page.html',
  styleUrls: ['./new-message.page.scss'],
})
export class NewMessagePage implements OnInit {

  constructor(
    private messageService: MessageService,
    private loadingCtrl: LoadingController,
    private modalCtrl: ModalController
    ) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm){
    if (!form.valid){
      return;
    }

    this.loadingCtrl.create({
      message: 'Sending message...'
    })
    .then(loadingEl => {
      loadingEl.present();
      const content = form.value.content;

      const model = {content}
      console.log(model);

      this.messageService
        .addMessage(
          model as Message
        )
        .subscribe(() => {
          loadingEl.dismiss();
          form.reset();
          this.modalCtrl.dismiss({dismissed: true})
        });
    });

  }

}
