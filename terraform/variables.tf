variable "aws_region" {
  description = "AWS region for resources"
  type        = "string"
  default     = "ap-south-1"
}

variable "environment" {
  description = "Deployment environment name"
  type        = "string"
  default     = "production"
}

variable "cluster_name" {
  description = "Name of the Amazon EKS cluster"
  type        = "string"
  default     = "orangehrm-cluster"
}

variable "vpc_cidr" {
  description = "VPC CIDR block"
  type        = "string"
  default     = "10.0.0.0/16"
}

variable "availability_zones" {
  description = "Availability zones for subnets"
  type        = list(string)
  default     = ["ap-south-1a", "ap-south-1b"]
}

variable "public_subnet_cidrs" {
  description = "CIDR blocks for public subnets"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24"]
}

variable "node_instance_types" {
  description = "EC2 instance types for EKS managed node group"
  type        = list(string)
  default     = ["t3.medium"]
}

variable "node_desired_size" {
  description = "Desired number of worker nodes"
  type        = number
  default     = 1
}

variable "node_min_size" {
  description = "Minimum number of worker nodes"
  type        = number
  default     = 1
}

variable "node_max_size" {
  description = "Maximum number of worker nodes"
  type        = number
  default     = 2
}

variable "ecr_repository_name" {
  description = "Name of the AWS ECR repository"
  type        = "string"
  default     = "test-images"
}
