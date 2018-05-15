# Steps to execute Speech API using cURL

1. Authenticate to Speech API by running `gcloud auth activate-service-account --key-file=<path_to_service_account_JSON_file>`
2. Get the authorization token by running `gcloud auth application-default print-access-token`
3. Call the API and pass the token value using a cURL script:

	curl -s -k -H 'Content-Type: application/json' \
      -H 'Authorization: Bearer <ACCESS_TOKEN> \
      'https://speech.googleapis.com/v1/speech:recognize' \
      -d @request.json
      