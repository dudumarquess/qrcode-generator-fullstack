import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QrCodeRequest } from './models/qr-code-request';
import { QrCodeResponse } from './models/qr-code-response';

@Injectable({
  providedIn: 'root'
})
export class QrCodeService {

  private apiUrl = 'http://localhost:8080/qrcode';

  constructor(private http: HttpClient) { }

  generateQrCode(request: QrCodeRequest): Observable<QrCodeResponse> {
    return this.http.post<QrCodeResponse>(this.apiUrl, request);
  }
}
