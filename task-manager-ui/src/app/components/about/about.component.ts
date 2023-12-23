import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrl: './about.component.scss',
  host: {ngSkipHydration: 'true'}
})
export class AboutComponent {

}
