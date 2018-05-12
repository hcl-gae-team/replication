test.py

import os
from pprint import pprint
from googleapiclient import discovery
from oauth2client.client import GoogleCredentials

credentials = GoogleCredentials.get_application_default()

service = discovery.build('iam', 'v1', credentials=credentials)
project_id = 'PROJECT_ID'

# You may use other service accounts. Follow format below:
# service_account = 'projects/{PROJECT_ID}/serviceAccounts/{SERVICE_ACCOUNT}'

# For reference - https://cloud.google.com/iam/reference/rest/v1/projects.serviceAccounts/delete

request = service.projects().serviceAccounts().delete(name=service_account)
response = request.execute()

pprint(response)