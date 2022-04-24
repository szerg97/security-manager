import { Component, OnDestroy, OnInit } from '@angular/core';
import { MenuController, SegmentChangeEventDetail } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Message } from 'src/app/_models/message';
import { MessageService } from 'src/app/_services/message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.page.html',
  styleUrls: ['./messages.page.scss'],
})
export class MessagesPage implements OnInit, OnDestroy {

  isInbox: boolean = true;

  loadedMessages: Message[];
  loadedInbox: Message[];
  loadedOutbox: Message[];

  isLoading = false;
  private messagesSub: Subscription;

  constructor(
    public messageService: MessageService,
    private menuCtrl: MenuController,
  ) { }

  ngOnInit() {
    this.messagesSub = this.messageService.messages.subscribe(msgs => {
      this.loadedMessages = msgs;
      this.loadedOutbox = this.loadedMessages.filter(x => x.toCustomer == false);
      this.loadedInbox = this.loadedMessages.filter(x => x.toCustomer == true);
      this.menuCtrl.enable(true);
    })
  }

  onFilter(event: Event){
    if((event as CustomEvent<SegmentChangeEventDetail>).detail.value == 'inbox'){
      this.isInbox = true;
    }
    else{
      this.isInbox = false;
    }
  }

  ionViewWillEnter() {
    this.isLoading = true;
    this.messageService.fetchMessages().subscribe(() => {
      this.isLoading = false;
    });
  }

  ngOnDestroy() {
    if (this.messagesSub) {
      this.messagesSub.unsubscribe();
    }
  }

}
