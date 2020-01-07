# narrative-reputation deployment for recovery

Requirements: 
- Requires the rep-application-recovery-yaml configmap
- Requires the rep-application-secret-yaml secret
- Requires the cloud-sql-proxy secret

# Create the application properties and secrets

Create the recovery configmap:
```
kubectl config use-context gke_recovery-narrative_us-west1_recovery-cluster
kubectl -n recovery create configmap rep-application-recovery-yaml --from-file=./application-recovery.yaml
```

Get the `production narrative-reputation - application-secret.yaml` file from 1Password. Create the secret:

```
kubectl -n recovery create secret generic rep-application-secret-yaml --from-file=./application-secret.yaml
```
Remove `application-secret.yaml`.

# Creating the cloud-sql-proxy IAM user and secret

See narrative-core for configuring these credentials. They should already exist.

# Deploy

Set context:

```
kubectl config use-context gke_recovery-narrative_us-west1_recovery-cluster
```

Create the recovery configmap (see above).


Create the recovery secret (see above).

Deploy narrative-reputation:

```
kubectl apply -f narrative-reputation-deployment.yml
```
