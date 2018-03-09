import { Component, OnInit } from '@angular/core';
import { TestService } from '../test-service.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  status: boolean;

  constructor(private testService: TestService) { }

  interval: any;

  ngOnInit(){
    this.start();
  }

   ngOnDestroy() {
   if (this.interval) {
       clearInterval(this.interval);
   }
  }

  stop() {
    console.log('stop');
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  start() {
    this.interval = setInterval(() => {
      this.testService.getStatus().subscribe(s => this.status = s) ;
     }, 1000);
  }

}
