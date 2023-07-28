# Employee App with JavaFX

This is a simple employee management application built using JavaFX and utilizing CSV (Comma-Separated Values) as the data source.

## Features
### [Login](docs/uiPictures/1loginview.png)
- Users who entered an incorrect username and/or password will not be able to access the application.

### [Employee](docs/uiPictures/2employeeview.png)
- View a list of employees stored in a CSV file.
- Add new employees to the CSV file.
- Edit employee information (name, hourly rate, position, etc.).
- Delete employees from the CSV file.
### [Attendance](docs/uiPictures/3attendanceview.png)
- View a list of attendances stored in a CSV file
- Add new attendance to the CSV file
- Edit attendance(date and time in or timeout)
- Delete attendance from CSV file.
### [Salary Calculator](docs/uiPictures/5salaryview.png)
- User can specify a employee number, select month by a combo box and input year
- User exceptions handler for invalid inputs.
- View breakdown of computations for weekly gross salary with weekly hours worked.
- View deductions(SSS, Philhealth, Pagibig) and Witholding Tax.
- View monthly gross salary and net salary.
### [Leave App](docs/uiPictures/4leavesview.png)
- View all employee with leaves by a tableview. 
- Evaluate how many credits have been spent for the employee number given.
- If the employee credits have already been depleted, no further leave can be scheduled.

## Requirements

- Java Development Kit (JDK) 8 or above.
- JavaFX.
- a compatible Integrated Development Environment (IDE) like IntelliJ IDEA.

## Setup

1. Clone or download the repository to your local machine.
2. Open the project in your preferred IDE.
3. Make sure the JavaFX library is properly configured in your project settings.

## Usage

1. Build and run the application.
2. The application will display login view.
3. Upon success login, user can view different scenes(Employee, Attendance, Leave and Salary Calculator).

## Employee Scene Usage
1. To <b>add new employee</b> select button named Add New Employee. Put values for entire fields and hit save.
2. To <b>delete employee</b>, select an item on tableview and hit Delete Employee button.
3. To <b>update employee</b>, select an item on tableiview and modify it via text fields, once finished modifying the fields, hit Update Button.
4. To <b>search employee</b>, enter any words on search field and hit Enter to execute your searched info.

## Author 
* [Jomari Abejo](https://www.instagram.com/reel/CuBs_WNNTjI/) --- [View Project Duration](https://wakatime.com/@jomariabejo/projects/hopwqjlntw?start=2023-07-22&end=2023-07-28)

## Acknowledgments 
* [Micah Andrew F. Bule](https://github.com/mabule-mmdc/)
* Mapúa Malayan Digital College, a new college of Malayan Colleges Laguna, a Mapúa school
