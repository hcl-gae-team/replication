# Steps to execute Speech API using cURL

1. Create `request.json` file for Speech API
2. Authenticate to Speech API by running `gcloud auth activate-service-account --key-file=<path_to_service_account_JSON_file>`
3. Get the authorization token by running `gcloud auth application-default print-access-token`
4. Call the API and pass the token value using a cURL script:

	curl -s -k -H 'Content-Type: application/json' \
      -H 'Authorization: Bearer <ACCESS_TOKEN> \
      'https://speech.googleapis.com/v1/speech:recognize' \
      -d @request.json