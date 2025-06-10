import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from '../../services/service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-sous-service',
  templateUrl: './add-sous-service.component.html',
  styleUrls: ['./add-sous-service.component.scss']
})
export class AddSousServiceComponent implements OnInit {
  sousServiceForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private serviceService: ServiceService,
    private dialogRef: MatDialogRef<AddSousServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { serviceId: number, serviceName: string },
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.sousServiceForm = this.fb.group({
      nom: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.sousServiceForm.valid) {
      this.isLoading = true;
      const sousServiceData = {
        ...this.sousServiceForm.value,
        serviceId: this.data.serviceId
      };

      this.serviceService.addSousService(sousServiceData).subscribe(
        response => {
          this.isLoading = false;
          this.snackBar.open('Sous-service ajouté avec succès!', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: ['success-snackbar']
          });
          this.dialogRef.close(response);
        },
        error => {
          this.isLoading = false;
          this.snackBar.open('Erreur lors de l\'ajout du sous-service', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: ['error-snackbar']
          });
          console.error('Erreur lors de l\'ajout du sous-service', error);
        }
      );
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}