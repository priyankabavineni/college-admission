**Submmitting a new Application**
 *Endpoint: POST/api/applicationsave
 *Input: ApplicationDto with applicationId, programId, sid (student ID), and adminId.
 *Procedure
         Validates if the program, student, and admin exist.
         Creates a new ApplicationEntity.
         Sets the default status to "applied".
         Saves the application to the database.
*Output: A standardized JSON response indicating success or failure.
**URl**        :http://localhost:8080/api/applicationsave
**RequestBody***
{
  "applicationId": " ",
  "programId": -,
  "sid": -,
  "adminId": -
}

**Getting All Applications**
 *Endpoint: GET /api/allApplications
 *Input   : None
 *Procedure
          Calls the service layer to fetch all applications from the repository.
 *output : A list of ApplicationEntity objects in a wrapped ResponseData.
**URL**  : http://localhost:8080/api/allApplications

**Getting Application By ID**
  *Endpoint:Get/api/id
  *Input : None
  *output: Getting the id of given if it is not there it prints id not found
**URL** : http://localhost:8080/api/applications/APP100


