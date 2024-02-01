import { Component } from '@angular/core';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class MyProfileComponent {

}
