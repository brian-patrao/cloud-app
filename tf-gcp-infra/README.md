# tf-gcp-infra

This repository contains Terraform configurations for setting up infrastructure on Google Cloud Platform (GCP). It is designed to streamline the process of provisioning and managing GCP resources in a repeatable and predictable manner.

## Overview

The infrastructure setup includes the creation of projects, VPCs, and other necessary GCP resources. This setup is ideal for developers and teams looking to automate their GCP infrastructure deployment using Terraform.

## Prerequisites

- Google Cloud SDK
- Terraform 0.12.x or newer

## GCP Setup

1. **Install Google Cloud SDK**: Follow the official documentation to install the Google Cloud SDK on your machine.
2. **Initialize gcloud**: Run `gcloud init` to authenticate and set up your GCP account.
3. **Create a GCP Project**: Use `gcloud projects create PROJECT_ID --name=PROJECT_NAME` to create a new project.

## Terraform Configuration

1. **Initialization**: Run `terraform init` to initialize a working directory containing Terraform configuration files.
2. **Configuration Files**: Create Terraform configuration files (.tf) to define the required resources.
3. **Planning**: Execute `terraform plan` to preview the changes Terraform will make to your infrastructure.
4. **Formatting**: Use `terraform fmt` to rewrite Terraform configuration files to a canonical format and style.
5. **Validation**: Run `terraform validate` to validate the configuration for syntax errors or inconsistencies.
6. **Apply Changes**: Deploy your infrastructure with `terraform apply` or `terraform apply -var='vpc_list=["vpc1","vpc2"]'` to specify variables.

## Infrastructure Architecture Overview

The designed architecture prioritizes security, scalability, and high availability, ensuring a robust and efficient system:

- **Virtual Private Cloud (VPC)**: Crafted with several subnets to isolate different service layers such as backend and database services, while securely managing traffic within the US-east region.
- **Managed Instance Group**: Serves the application's backend services, with auto-scaling capabilities to meet varying traffic demands.
- **Cloud SQL**: Offers managed SQL database services with private connections, safeguarding data integrity and security.
- **Cloud DNS and SSL**: Employs Google-managed SSL certificates and DNS configurations to facilitate secure and dependable user connections.
- **GitHub Actions and HashiCorp Packer**: Incorporates CI/CD pipelines for seamless integration, automated testing, and the creation of optimized machine images.
- **Google Cloud Pub/Sub and Cloud Functions**: Leverages an event-driven architecture for streamlined account management and email notifications.
- **Security with IAM and KMS**: Enforces stringent IAM roles and utilizes the Key Management Service for managing encryption keys, enhancing data security and compliance.

## Principal Technologies

- **Google Cloud Platform**: Utilizes a wide array of GCP services, including VPC, Cloud SQL, Cloud Functions, Pub/Sub, Cloud DNS, and IAM, to build a comprehensive cloud infrastructure.
- **Terraform**: Employs Terraform for the provisioning and management of cloud resources through code, ensuring consistent and repeatable infrastructure setups.
- **GitHub Actions**: Facilitates continuous integration and deployment workflows, promoting consistent testing, integration, and deployment practices.
- **Packer**: Creates custom machine images pre-configured with necessary dependencies, improving deployment efficiency and operational performance.

## CI/CD and DevOps Methodologies

- **Continuous Integration**: Implements automated testing and building of application code upon changes, maintaining code quality and minimizing integration issues.
- **Infrastructure as Code (IaC)**: Utilizes Terraform to codify infrastructure provisioning, offering enhanced deployment agility and infrastructure transparency.

## Configuration and Deployment Instructions

Comprehensive guide on repository cloning, dependency installation, and application deployment via Terraform:

1. **Initial Environment Configuration**: Instructions on setting up the `gcloud` CLI, authenticating your session, and configuring Terraform for GCP.
2. **Executing Infrastructure Deployment**: Detailed steps to initialize Terraform, create a deployment plan, and apply the configuration to set up the necessary resources.

## Security Protocols

Details on the implemented security measures, including VPC configurations, IAM role assignments, and the application of GCP's Key Management Service for encryption key handling.

## Monitoring and Management

Insight into the application's monitoring and logging strategies using Google Cloud's operations suite, ensuring continuous insight into application health and performance.
