var app = angular.module('StudentApp', []);

// Локализация
app.constant('messages', {
    'studentAdded': {
        'ru': 'Студент успешно добавлен',
        'en': 'Student successfully added'
    },
    'studentDeleted': {
        'ru': 'Студент успешно удален',
        'en': 'Student successfully deleted'
    },
    'studentNotFound': {
        'ru': 'Студент не найден',
        'en': 'Student not found'
    },
    'studentFound': {
        'ru': 'Студент найден',
        'en': 'Student found'
    },
    'addStudentError': {
        'ru': 'Ошибка при добавлении студента',
        'en': 'Error adding student'
    }
});

app.controller('StudentController', function($scope, $http, $window, messages) {
    // Устанавливаем начальный язык (по умолчанию 'ru')
    $scope.language = 'ru';

    // Функция для получения локализованного текста
    $scope.getLocalizedText = function(key) {
        return messages[key] && messages[key][$scope.language] ? messages[key][$scope.language] : key;
    };

    // Функция для перезагрузки локализованных сообщений
    $scope.reloadMessages = function() {
        $scope.studentAdded = $scope.getLocalizedText('studentAdded');
        $scope.studentDeleted = $scope.getLocalizedText('studentDeleted');
        $scope.studentNotFound = $scope.getLocalizedText('studentNotFound');
        $scope.studentFound = $scope.getLocalizedText('studentFound');
        $scope.addStudentError = $scope.getLocalizedText('addStudentError');
    };

    $scope.students = [];
    $scope.newStudent = {};
    $scope.deleteStudentId = null; // ID для удаления студента
    $scope.searchStudentId = null; // ID для поиска студента
    $scope.searchedStudent = null; // Найденный студент
    $scope.message = '';  // Сообщение об успешной операции
    $scope.errorMessage = '';  // Сообщение об ошибке

    // Очистка сообщений
    function clearMessages() {
        $scope.message = '';
        $scope.errorMessage = '';
    }

    // Очистка полей формы
    function clearForms() {
        $scope.newStudent = {};
        $scope.deleteStudentId = null;
        $scope.searchStudentId = null;
        $scope.searchedStudent = null;
    }

    // Получение списка студентов
    $scope.loadStudents = function() {
        clearMessages();
        $http.get('/api/students')
            .then(function(response) {
                // Получаем язык из заголовка ответа
                $scope.language = response.headers('Content-Language');  // Устанавливаем язык по умолчанию, если заголовок отсутствует
                $scope.reloadMessages();  // Перезагружаем локализованные сообщения в зависимости от языка
                $scope.students = response.data;
            }, function(error) {
                $scope.errorMessage = $scope.getLocalizedText('studentNotFound');
            });
    };

    // Добавление нового студента
    $scope.addStudent = function() {
        clearMessages();
        $http.post('/api/students', $scope.newStudent, {
            headers: { 'Content-Type': 'application/json' }
        }).then(function(response) {
            $scope.loadStudents();
            clearForms();
            $scope.message = $scope.getLocalizedText('studentAdded');
        }, function(error) {
            $scope.errorMessage = error.data.error || $scope.getLocalizedText('addStudentError');
        });
    };

    // Удаление студента по ID
    $scope.deleteStudent = function() {
        clearMessages();
        $http.delete('/api/students/' + $scope.deleteStudentId)
            .then(function(response) {
                $scope.loadStudents();
                clearForms();
                $scope.message = $scope.getLocalizedText('studentDeleted');
            }, function(error) {
                $scope.errorMessage = error.data.error || $scope.getLocalizedText('studentNotFound');
            });
    };

    // Поиск студента по ID
    $scope.findStudentById = function() {
        clearMessages();
        $http.get('/api/students/' + $scope.searchStudentId)
            .then(function(response) {
                $scope.searchedStudent = response.data;
                $scope.message = $scope.getLocalizedText('studentFound');
            }, function(error) {
                $scope.errorMessage = error.data.error || $scope.getLocalizedText('studentNotFound');
            });
    };

    // Загрузка данных при инициализации
    $scope.loadStudents();
});
