aws_region          = "ap-south-1"
environment         = "production"
cluster_name        = "orangehrm-cluster"
ecr_repository_name = "test-images"
node_instance_types = ["t3.medium"]
node_desired_size   = 1
node_min_size       = 1
node_max_size       = 2
