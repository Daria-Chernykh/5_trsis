var app = angular.module('studentApp', []);

app.controller('StudentController', function ($scope, $http) {
    $scope.students = [];
    $scope.newStudent = {};

    // Загрузка всех студентов
    $scope.loadStudents = function () {
        $http.get('/students').then(function (response) {
            $scope.students = response.data;
        }, function (error) {
            console.log('Ошибка загрузки студентов', error);
        });
    };

    // Добавление нового студента
    $scope.addStudent = function () {
        $http.post('/add', $scope.newStudent).then(function (response) {
            $scope.students.push(response.data);
            $scope.newStudent = {}; // Очистка формы
        }, function (error) {
            console.log('Ошибка добавления студента', error);
        });
    };

    // Удаление студента
    $scope.deleteStudent = function(fullName) {
        if (fullName) {
            console.log('Удаление студента с именем:', fullName);
            $http({
                method: 'POST',
                url: '/delete',
                params: { name: fullName }
            }).then(function(response) {
                console.log('Студент успешно удален:', response.data);

                $scope.students = $scope.students.filter(student => student.fullName !== fullName);
            }, function(error) {
                console.log('Ошибка удаления студента:', error);
            });
        } else {
            console.log('Ошибка: имя студента не определено');
        }
    };

    $scope.loadStudents();
});
