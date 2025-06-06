import { Component } from '@angular/core';
import { QrCodeService } from '../qr-code.service';

@Component({
  selector: 'app-qr-code-generator',
  templateUrl: './qr-code-generator.component.html',
  styleUrl: './qr-code-generator.component.css'
})
export class QrCodeGeneratorComponent {

  textInput: string = '';
  qrCodeUrl: string = '';
  errorMessage: string = ''; 
  currentYear: number = new Date().getFullYear();

  constructor(private qrCodeService: QrCodeService) { }

  generateQrCode() {
    this.qrCodeUrl = '';
    this.errorMessage = ''; 
    this.qrCodeService.generateQrCode({ text: this.textInput }).subscribe({
      next: (response) => this.qrCodeUrl = response.url,
      error: (error) => {
        console.error('Error generating QR code:', error); 
        this.qrCodeUrl = "Error generating QR code. Please try again.";
      } 
    });
  }

  

  downloadQrCode() {
  if (!this.qrCodeUrl) return;
  window.open(this.qrCodeUrl, '_blank');
}
}
