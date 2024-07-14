# Serverless Email Verification Function

## Overview
This project implements a serverless function designed to handle email verification processes. 
Utilizing Google Cloud Functions and Pub/Sub, the function listens for incoming messages, decodes them, and sends an email verification link to the user's email address. 
This is part of a larger system aimed at enhancing security and user experience by ensuring that email addresses are verified.

## Technologies Used
- Java
- Spring Boot
- Maven
- Google Cloud Functions (GCF)
- Google Cloud Pub/Sub
- MySQL

## Functionality
The core functionality of this serverless function includes:
- **Receiving Messages**: Listens to Pub/Sub messages that contain user email addresses.
- **Decoding Messages**: Extracts and decodes the base64 encoded email addresses from the messages.
- **Database Interaction**: Updates the database with a unique verification token and its generation time for the user.
- **Email Sending**: Constructs a verification link and sends it to the user's email address using the Mailgun API.

## Setup and Deployment
1. **Environment Variables**: Ensure that `DB_USER`, `DB_PASS`, `DB_NAME`, `DB_PORT`, and `DB_HOST` environment variables are set for database connectivity (Github secrets).
2. **Google Cloud Setup**: Configure Google Cloud Functions and Pub/Sub to work with the function.
3. **Mailgun Configuration**: Set your domain name and API key in the function's constants for email sending capabilities.
4. **Deployment**: Use Maven to build the project and deploy it to Google Cloud Functions.

## Security Notes
- Always keep your API keys and database credentials secure and never expose them in your codebase.
- Ensure that the email verification process is secure and resistant to tampering.
