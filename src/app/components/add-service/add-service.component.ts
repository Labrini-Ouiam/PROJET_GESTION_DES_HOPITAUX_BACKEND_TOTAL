import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from '../../services/service.service';
import { HopitalService } from '../../services/hopital.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.scss']
})
export class AddServiceComponent implements OnInit {
  serviceForm: FormGroup;
  hopitaux = [];
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private serviceService: ServiceService,
    private hopitalService: HopitalService,
    private dialogRef: MatDialogRef<AddServiceComponent>,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.loadHopitaux();
  }

  initForm(): void {
    this.serviceForm = this.fb.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
      hopital: [null, Validators.required]
    });
  }

  loadHopitaux(): void {
    this.hopitalService.getAllHopitaux().subscribe(
      data => this.hopitaux = data,
      error => console.error('Erreur lors du chargement des hôpitaux', error)
    );
  }

  onSubmit(): void {
    if (this.serviceForm.valid) {
      this.isLoading = true;
      const serviceData = {
        ...this.serviceForm.value,
        hopital: {
          idHopital: this.serviceForm.value.hopital
        }
      };

      this.serviceService.addService(serviceData).subscribe(
        response => {
          this.isLoading = false;
          this.snackBar.open('Service ajouté avec succès!', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: ['success-snackbar']
          });
          this.dialogRef.close(response);
        },
        error => {
          this.isLoading = false;
          this.snackBar.open('Erreur lors de l\'ajout du service', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: ['error-snackbar']
          });
          console.error('Erreur lors de l\'ajout du service', error);
        }
      );
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}