# narrative-web-front-end deployment for recovery

Refer to `../recovery-jenkins.md` for configuring the Jenkins Deployment Service Account.

To deploy the narrative web front end for recovery:

```
kubectl apply -f narrative-web-front-end-deployment.yml
kubectl apply -f narrative-web-front-end-service.yml
kubectl apply -f narrative-web-front-end-hpa.yml
```

Configure the oauth settings (<https://console.cloud.google.com/apis/credentials/oauthclient/1026630561657-4kkm1aoe71ldcvp4rkd886ghjgqemv8a.apps.googleusercontent.com?project=operations-204322&organizationId=149049775531>). This requires two redirects for each site:

For recovery:
```
https://recovery.narrative.org
https://recovery.narrative.org/oauth2/callback
```
Configure Cloudflare DNS to point to the `recovery-narrative` GKE cluster ingress (<https://console.cloud.google.com/kubernetes/list?project=recovery-narrative&organizationId=149049775531>). Set and A record for `recovery.narrative.org`. 

