**SaveStudent** 
  *Endpoint:POST /student/save
  *Input : Request Body: StudentDTO containing:
           sid (int): The student ID.
           sname (String): The student's name.
           email (String): The student's email address (must end with "@gmail.com").
  *Procedure : This endpoint saves the provided student data to the database.
               It validates the student's name (minimum 10 characters) and email (must end with "@gmail.com").
               On successful creation, the student is saved and the response will return the created student details.
  *Output: Response Body:
               status: "success" or "failed"
               message: A message describing the outcome (e.g., "saved successfully" or error message).
               data: The saved StudentDTO (if success).
**URL** : POST http://localhost:8080/student/save
**RequestBody**
{
  "sid": 1,
  "sname": "John Doe",
  "email": "john.doe@gmail.com"
}

**StudentStaus & Applications**
  *Endpoint: GET /student/status
  *Input: Query Parameter: sid (int): The student ID.
  *Procedure:This endpoint fetches the details of a student using their ID (sid).
             It also fetches all applications related to the student and returns both in the response.
  *Output : Response Body:status: "success" or "failed"
            message: (optional) A message describing the outcome.
            data: An object containing student (the StudentDTO) and applications (a list of application details if any).
 **URl** : GET http://localhost:8080/student/status?sid=1
 **RequestBody** : "None"
