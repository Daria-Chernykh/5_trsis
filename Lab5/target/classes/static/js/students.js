var app = angular.module('students', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
});

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
        $scope.studentsList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetStudentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on student fullName "+$scope.inputFullName;
    };

    $scope.successAddStudentCallback = function (response) {
        $http.get('/public/rest/students').then($scope.successGetStudentsCallback, $scope.errorGetStudentsCallback);
        $scope.errormessage="";
    };

    $scope.errorAddStudentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new student";
    };

    $scope.addStudent = function () {
        const studentData = {
            "fullName": $scope.inputFullName,
            "taskOption": $scope.inputTaskOption,
            "number": $scope.inputNumber,
            "rating": $scope.inputRating
          };
        $http.post('/public/rest/students', studentData).then($scope.successAddStudentCallback, $scope.errorAddStudentCallback);
    };
});
