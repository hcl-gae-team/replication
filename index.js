'use strict';

const {GoogleApis} = require("googleapis");
const google = new GoogleApis();
const projectId = "PROJECT_ID";
const location = "global";

google.auth.getApplicationDefault((err, authClient) => {
  if (err) {
    console.error('Failed to acquire credentials');
    return;
  }

  if (authClient.createScopedRequired && authClient.createScopedRequired()) {
    authClient = authClient.createScoped([
      'https://www.googleapis.com/auth/cloud-platform'
    ]);
  }

  // Instantiates an authorized client
  const cloudkms = google.cloudkms({
    version: 'v1',
    auth: authClient
  });
  const request = {
    name: `projects/${projectId}`
  };

  // Lists key rings
  cloudkms.projects.locations.list(request, (err, result) => {
    if (err) {
      console.error(err);
      return;
    }

    const locations = result.locations || [];

    if (locations.length) {
      console.log('Locations:');
      result.locations.forEach((location) => console.log(location.name));
    } else {
      console.log(`No locations found.`);
    }
  });
});

