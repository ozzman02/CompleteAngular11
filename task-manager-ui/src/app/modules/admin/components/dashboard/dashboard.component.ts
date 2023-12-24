import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  host: { ngSkipHydration: 'true' }
})
export class DashboardComponent implements OnInit {

  Designation: string = "";
  Username: string = "";
  NoOfTeamMembers: number = 0;
  TotalCostOfAllProjects: number = 0;
  PendingTasks: number = 0;
  UpComingProjects: number = 0;
  ProjectCost: number = 0;
  CurrentExpenditure: number = 0;
  AvailableFunds: number = 0;
  Today: Date;

  Clients: string[] = [];
  Projects: string[] = [];
  Years: number[] = [];
  TeamMembersSummary: any = [];
  TeamMembers: any = [];

  constructor(private dasboardService: DashboardService) {

  }

  ngOnInit(): void {
    this.Designation = 'Team Leader';
    this.Username = 'Scott Smith';
    this.NoOfTeamMembers = 67;
    this.TotalCostOfAllProjects = 240;
    this.PendingTasks = 15;
    this.UpComingProjects = 0.2;
    this.ProjectCost = 2113507;
    this.CurrentExpenditure = 96788;
    this.AvailableFunds = 52536;
    this.Today = new Date();

    this.Clients = this.dasboardService.getClientsTestData();

    this.Projects = this.dasboardService.getProjectsTestData();

    this.Years = this.dasboardService.getYearsTestData();

    this.TeamMembersSummary = this.dasboardService.getTeamMembersSummaryTestData();

    this.TeamMembers = this.dasboardService.getTeamMembersTestData();
  }

  onProjectChange($event: any): void {
    console.log($event.target.innerHTML);
    if ($event.target.innerHTML.trim() == 'Project A') {
      this.ProjectCost = 2113507;
      this.CurrentExpenditure = 96788;
      this.AvailableFunds = 52436;
    } else if ($event.target.innerHTML.trim() == 'Project B') {
      this.ProjectCost = 88923;
      this.CurrentExpenditure = 22450;
      this.AvailableFunds = 2640;
    } else if ($event.target.innerHTML.trim() == 'Project C') {
      this.ProjectCost = 662183;
      this.CurrentExpenditure = 7721;
      this.AvailableFunds = 9811;
    } else if ($event.target.innerHTML.trim() == 'Project D') {
      this.ProjectCost = 928431;
      this.CurrentExpenditure = 562;
      this.AvailableFunds = 883;
    }
  }

}
