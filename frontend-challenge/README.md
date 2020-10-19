The Enrollee Tracker application interface consists of a single tab for the management of enrollees.

Developed with Angular 10, it provides the ability to retrieve, add, update, and delete enrollees. The enrollees tab allows for the editing of an enrollee, or modification of the total set of enrollees. The 'Manage' drop down on the right has options to save changes to the current set of loaded enrollees, or load the current set of enrollees.

The back-end service endpoint can be configured in '/frontend-challenge/src/environments/environment.ts' by setting apiUrl. It is defautled to 'http://localhost:8080/apiv1' which is the endpoint of the companion back-end service.

To rebuild dependencies 'npm install' will need to be executed from the project directory.


Cross-origin resource sharing will need to be un-blocked if the backend-service and the frontend are running on your local machine.