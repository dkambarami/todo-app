import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  message = 'This is the welcome component';
  welcomeMessageFromService: string;

  username: string;

  constructor(private route: ActivatedRoute, private welcomeDataSevice: WelcomeDataService) {


  }

  ngOnInit(): void {
    this.username = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    this.welcomeDataSevice.executeHelloWorldBeanService().subscribe(
      response => this.handleSuccessfulResponse(response), error => this.handleErrorResponse(error)
    );
  }

  getWelcomeMessageWithVariable(name: string) {
    this.welcomeDataSevice.executeHelloWorldBeanServiceWithPath(name).subscribe(
      response => this.handleSuccessfulResponse(response), error => this.handleErrorResponse(error)
    );
  }

  handleSuccessfulResponse(response) {
    //console.log(response.message);
    this.welcomeMessageFromService = response.message;
  }

  handleErrorResponse(error) {
    this.welcomeMessageFromService = error.error.message;
  }

}
