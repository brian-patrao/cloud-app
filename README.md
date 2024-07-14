# Cloud Application Project Overview

This repository is a comprehensive suite for a cloud-based application, encompassing serverless functions, infrastructure as code, and a web application. It leverages modern technologies such as Java, Spring Boot, and Maven, alongside Google Cloud Platform (GCP) for a scalable and secure deployment.

## Structure

- **Serverless**: Contains the cloud function responsible for email verification processes, enhancing security and user experience. [Read more](serverless-main/README.md)
- **tf-gcp-infra**: Holds the Terraform configurations for provisioning and managing GCP resources, ensuring a repeatable and predictable infrastructure setup. [Read more](tf-gcp-infra/README.md)
- **webapp**: The core application code, built with Java, Spring Boot, and Maven, providing the REST API service for managing web resources. [Read more](webapp/README.md)

## Quick Links

- [Commit History on my NEU GitHub for webapp](https://github.com/BrianPOrganization/webapp)
- [Commit History on my NEU GitHub for serverless](https://github.com/BrianPOrganization/tf-gcp-infra)
- [Commit History on my NEU GitHub for tf-gcp-infra](https://github.com/brianpatraoneu)

## Getting Started

To begin working with this project, clone the repository to your local machine:

```bash
git clone <repository-url>
```

Navigate into each directory (`serverless`, `tf-gcp-infra`, `webapp`) for detailed instructions on setup, deployment, and usage.

## Technologies

- **Java**: For building the web application and serverless functions.
- **Spring Boot**: Simplifies the development of new Spring applications.
- **Maven**: Dependency management and project build tool.
- **Terraform**: Used for defining and provisioning the cloud infrastructure on GCP.
- **Google Cloud Platform**: Hosting and running the application, database, and serverless functions.
- **Cloud SQL**: Google Cloud's fully managed relational database service for MySQL, PostgreSQL, and SQL Server.