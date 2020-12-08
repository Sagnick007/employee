<!DOCTYPE html>
<html>
<body>

<h1>The form method="post" attribute</h1>

<form action="/employee" method="post" target="_blank">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name"><br><br>
  <label for="designation">Designation:</label>
  <input type="text" id="designation" name="designation"><br><br>
  <label for="reporting_manager">Reporting Manager:</label>
  <input type="text" id="reportingManager" name="reportingManager"><br><br>
  <label for="name">Department:</label>
  <input type="text" id="department" name="department"><br><br>
  <label for="name">Phone:</label>
  <input type="text" id="phone" name="phone"><br><br>
  <label for="email">Email:</label>
  <input type="text" id="email" name="email"><br><br>
  <label for="location">Location:</label>
  <input type="text" id="location" name="location"><br><br>
  <input type="submit" value="Submit">
</form>

<p>Click on the submit button, and the form will be submittied using the POST method.</p>

</body>
</html>