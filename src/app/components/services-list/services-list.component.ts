import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ServiceService } from '../../services/service.service';
import { AddServiceComponent } from '../add-service/add-service.component';
import { AddSousServiceComponent } from '../add-sous-service/add-sous-service.component';

@Component({
  selector: 'app-services-list',
  templateUrl: './services-list.component.html',
  styleUrls: ['./services-list.component.scss']
})
export class ServicesListComponent implements OnInit {
  services = [];
  selectedHopitalId: number;

  constructor(
    private serviceService: ServiceService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    // Initialisation
  }

  loadServices(hopitalId: number): void {
    this.selectedHopitalId = hopitalId;
    this.serviceService.getServicesByHopital(hopitalId).subscribe(
      data => this.services = data,
      error => console.error('Erreur lors du chargement des services', error)
    );
  }

  openAddServiceDialog(): void {
    const dialogRef = this.dialog.open(AddServiceComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Si un service a été ajouté, recharger la liste
        if (this.selectedHopitalId) {
          this.loadServices(this.selectedHopitalId);
        }
      }
    });
  }

  openAddSousServiceDialog(serviceId: number, serviceName: string): void {
    const dialogRef = this.dialog.open(AddSousServiceComponent, {
      width: '500px',
      data: { serviceId, serviceName }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Si un sous-service a été ajouté, recharger la liste
        if (this.selectedHopitalId) {
          this.loadServices(this.selectedHopitalId);
        }
      }
    });
  }
}