# QR Code Generator with S3 Upload
This project receives a text input, generates a QR code image based on that text, and uploads the resulting image to an Amazon S3 bucket. It's a simple and effective tool for automating QR code creation and cloud storage.

### Features
- Accepts any text input

- Generates a QR code image from the text

- Uploads the image to a specified AWS S3 bucket

- Returns the image URL or reference after upload

## How to Run?
1. Configure the .env file with AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
2. run `docker build -t qrcode-generator:1.0 .`
3. run `docker run --env-file .env -p 8080:8080 qrcode-generator:1.0`
