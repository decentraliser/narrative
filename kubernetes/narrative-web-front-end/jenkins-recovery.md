# Configuring the Jenkins Deployment Service Account

This allows for Jenkins to deploy to recovery-cluster.

If the Jenkins user doesn't exist (note the project is the `informationtechnology` project):

```
gcloud config configurations activate informationtechnology
gcloud iam service-accounts create jenkins --display-name "Jenkins service account."
gcloud iam service-accounts keys create ~/.config/gcloud/jenkins.json --iam-account jenkins@informationtechnology-205813.iam.gserviceaccount.com
```

Save the `jenkins.json` file in 1Password.

Give the service account access to the cluster:

```
gcloud projects add-iam-policy-binding recovery-narrative --role=roles/container.viewer --member=serviceAccount:jenkins@informationtechnology-205813.iam.gserviceaccount.com
```

Apply the cluster role to the Jenkins account:

```
kubectl config use-context gke_recovery-narrative_us-west1_recovery-cluster
kubectl create -f recovery/role.yml
```

# Deploy Key

In order for Jenkins to pull the Git repository, add the Jenkins SSH deploy key to the GitHub repo keys.

Create the SSH key and save in 1Password (should already exist as 'jenkins ssh key - narrative-platform repository').
