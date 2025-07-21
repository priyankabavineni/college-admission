**Update Application Status**
Endpoint: PUT/admin/update-status
Description: Updates the status of an application by a given admin.
**Parameters** :applicationId (query param, String): The ID of the application to update.
                adminId (query param, int): The ID of the admin updating the status.
                status (query param, String): The new status to set (e.g., "APPROVED", "REJECTED").
**URL** : http://localhost:8080/admin/update-status?applicationId=a11&adminId=91&status=REJECTED
**ResponseExample** //for success
{
  "status": "success",
  "data": {
    "applicationId": "app123",
    "status": "APPROVED",
    "admin": {
      "adminId": 1,
      // other admin details
    }
    // other application entity fields
  }
}
**ResponseExample** //for failure
{
  "status": "failed",
  "message": "Application id not found: app123"
}

**View All Applications**
EndPoint: GET/admin/applications
Description: Retrieves the list of all application entities.
**Parameters** : None
**URL** : http://localhost:8080/admin/applications
Output: All the applications are appeared in vscode







