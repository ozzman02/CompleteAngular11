import { Component, OnInit } from '@angular/core';
import { User } from './models/user';
import { LoginService } from './services/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class AppComponent {
  
  title = 'task-manager-ui';

  constructor(public loginService: LoginService) {}

  onSearchClick() {
    console.log(this.loginService.currentUserName);
  }

  
  
}
