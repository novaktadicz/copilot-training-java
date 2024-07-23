import { Component } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  userInput: string = '';
  messages: { sender: string, text: string }[] = [];
  sessionId: string = '';

  constructor(private chatService: ChatService) {
    // generate random session id
    this.sessionId = Math.random().toString(36).substring(7);
  }

  sendMessage(): void {
    const userMessage = this.userInput.trim();
    if (userMessage) {
      this.messages.push({ sender: 'user', text: userMessage });
      this.chatService.generateResponse(this.sessionId, userMessage).subscribe(response => {
        this.messages.push({ sender: 'bot', text: response });
      });
      this.userInput = '';
    }
  }
}
