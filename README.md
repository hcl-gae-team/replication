# Install Python libraries

1. Run `sudo pip install pprint`
2. Make sure to authenticate the API first
	- go to IAM and create a new service account named replication
	- generate the key and upload the JSON file to a GCS bucket
	- go back to cloud shell and copy the JSON file
		gsutil cp gs://rep-team/hcl-karen-replication-b7c20c46efa3.json .
	- set global variable for application default credentials
		export GOOGLE_APPLICATION_CREDENTIALS=hcl-karen-replication-b7c20c46efa3.json
	- in test.py, update the service account name
	- Replace `PROJECT_ID` with your project ID
4. Execute the file by `python test.py`
