var app = angular.module('students', []);

app.controller("StudentsController", function ($scope, $http) {

    $scope.successGetStudentsCallback = function (response) {
        $scope.studentsList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetStudentsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of students";
    };

    $scope.getStudents = function () {
        $http.get('/public/rest/students').then($scope.successGetStudentsCallback, $scope.errorGetStudentsCallback);
    };

    $scope.successDeleteStudentCallback = function (response) {
        for (var i = 0; i < $scope.studentsList.length; i++) {
            var j = $scope.studentsList[i];
            if (j.id === $scope.deletedId) {
                $scope.studentsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteStudentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete student; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteStudent = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/students/' + id).then($scope.successDeleteStudentCallback, $scope.errorDeleteStudentCallback);
    };


    $scope.successGetStudentCallback = function (response) {
        $scope.studentsList.push(response.data); // Добавление в конец
        $scope.errormessage = "";
    };

    $scope.errorGetStudentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on student with ID "+$scope.inputID;
    };

    $scope.successAddStudentCallback = function (response) {
        $http.get('/public/rest/students/fullName/' + $scope.inputFullName).then($scope.successGetStudentCallback, $scope.errorGetStudentCallback);
        $scope.errormessage="";
    };

    $scope.errorAddStudentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new student; check if it's number is unique";
    };

    $scope.addStudent = function () {
        const studentData = {
            "fullName": $scope.inputFullName,
            "taskOption": $scope.inputTaskOption,
            "number": $scope.inputNumber,
            "rating": $scope.inputRating
        };

        $http.post('/public/rest/students', studentData).then(
            function(response) {
                $scope.successAddStudentCallback(response);

                // Очищение полей после успешного добавления
                $scope.inputFullName = '';
                $scope.inputTaskOption = '';
                $scope.inputNumber = '';
                $scope.inputRating = '';
            },
            function(error) {
                $scope.errorAddStudentCallback(error);
            }
        );
    };
});
