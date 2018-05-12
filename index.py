def run_quickstart():
    # [START kms_quickstart]
    # Imports the Google APIs client library
    import googleapiclient.discovery

    # Your Google Cloud Platform project ID
    project_id = 'PROJECT_ID'

    # Lists keys in the "global" location.
    location = 'global'

    # Creates an API client for the KMS API.
    kms_client = googleapiclient.discovery.build('cloudkms', 'v1')

    # The resource name of the location associated with the key rings.
    name = 'projects/{}'.format(project_id)

    # Lists key rings
    request = kms_client.projects().locations().list(name=name)
    response = request.execute()

    if 'locations' in response and response['locations']:
        print('Key rings:')
        for key_ring in response['locations']:
            print(key_ring['name'])
    else:
        print('No key rings found.')
    # [END kms_quickstart]


if __name__ == '__main__':
    run_quickstart()